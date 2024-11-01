package packagetracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import packagetracking.Courier.Courier;

import java.util.HashMap;
import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public Package createPackage(Package myPackage) {
        if(myPackage == null) {
            throw new IllegalArgumentException("Package must not be null");
        }
        return packageRepository.save(myPackage);
    }

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Package updatePackage(Integer id, Package myPackage) {
        Package existingPackage = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        if(myPackage.getStatus() != null){
            existingPackage.setStatus(myPackage.getStatus());
        }
        if(myPackage.getCourier() != null){
            existingPackage.setCourier(myPackage.getCourier());
        }
        if(myPackage.getDeliveryAddress() != null){
            existingPackage.setDeliveryAddress(myPackage.getDeliveryAddress());
        }

        return packageRepository.save(existingPackage);
    }

    public void deletePackage(Integer id) {
        if(!packageRepository.existsById(id)) {
            throw new RuntimeException("Package not found");
        }
        packageRepository.deleteById(id);
    }

    public void deleteAllPackagesByCourier(Courier courier) {
        List<Package> packageList = packageRepository.findPackageByCourier(courier);
        for(Package p : packageList) {
            deletePackage(p.getId());
        }
    }

    public List<Package> getPackagesForCourier(Courier courier) {
        return packageRepository.findPackageByCourier(courier);
    }

    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return packageRepository.findAllCouriersWithoutPendingPackages();
    }

    public HashMap<Integer,Integer> getAllManagersAndDeliveredNumber(){
        HashMap<Integer,Integer> managerMap = new HashMap<>();
        List<Package> packageList = packageRepository.findPackagesByStatus(Status.valueOf("DELIVERED"));
        for(Package p : packageList) {
            Courier courier = p.getCourier();
            if(courier.getManager() != null){
                if(!managerMap.containsKey(courier.getManager().getId())) {
                    managerMap.put(courier.getManager().getId(), 1);
                }else {
                    managerMap.put(courier.getManager().getId(), managerMap.get(courier.getManager().getId()) + 1);
                }
            }else {
                managerMap.put(courier.getId(), managerMap.get(courier.getId()) + 1);
            }
        }
        return managerMap;
    }
}
