package packagetracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import packagetracking.pkg.PackageService;

import java.util.List;

@Service
public class CourierService {

    @Autowired
    public CourierRepository courierRepository;
    @Autowired
    public PackageService packageService;

    public Courier createCourier(Courier courier) {
        if(courier == null){
            throw new NullPointerException("Courier is null");
        }
        return courierRepository.save(courier);
    }

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Courier updateCourier(Integer id, Courier courier) {
        Courier existingCourier = courierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Courier not found"));

        if(courier.getEmail() != null){
            existingCourier.setEmail(courier.getEmail());
        }
        if(courier.getStatus() != null){
            existingCourier.setStatus(courier.getStatus());
        }
        if(courier.getManagerId() != null){
            existingCourier.setManagerId(courier.getManagerId());
        }
        return courierRepository.save(existingCourier);
    }

    public void deleteCourier(Integer id) {
        if(!courierRepository.existsById(id)){
            throw new IllegalArgumentException("Courier not found");
        }
        Courier courier = courierRepository.findById(id).orElse(null);
        packageService.deleteAllPackagesByCourier(courier);
        courierRepository.deleteById(id);
    }

    public Courier getCourier(Integer id) {
        return courierRepository.findById(id).orElse(null);
    }
}
