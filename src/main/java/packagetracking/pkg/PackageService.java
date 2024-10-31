package packagetracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(myPackage.getCurier() != null){
            existingPackage.setCurier(myPackage.getCurier());
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
}