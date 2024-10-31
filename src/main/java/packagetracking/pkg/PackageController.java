package packagetracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import packagetracking.Courier.Courier;
import packagetracking.Courier.CourierService;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;
    @Autowired
    private CourierService courierService;

    @PostMapping("/package")
    private Package create(@RequestBody Package myPackage) {
        return packageService.createPackage(myPackage);
    }

    @GetMapping("/package")
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @PutMapping("/package/{id}")
    private Package update(@PathVariable Integer id, @RequestBody Package myPackage) {
        return packageService.updatePackage(id, myPackage);
    }

    @DeleteMapping("/package/{id}")
    private void delete(@PathVariable Integer id) {
        packageService.deletePackage(id);
    }

    @GetMapping("/package/courier/{courierId}")
    public List<Package> getCourierPackages(@PathVariable Integer courierId) {
        Courier courier = courierService.getCourier(courierId);
        return packageService.getPackagesForCourier(courier);
    }

    @GetMapping("/package/pending")
    public List<Courier> getPendingPackages() {
        return packageService.getAllCouriersWithoutPendingPackages();
    }
}
