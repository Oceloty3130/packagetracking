package packagetracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping("/package")
    private Package create(@RequestBody Package myPackage) {
        return packageService.createPackage(myPackage);
    }

    @GetMapping("/package")
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @PutMapping("/package/{id}")
    private Package update(@PathVariable int id, @RequestBody Package myPackage) {
        return packageService.updatePackage(id, myPackage);
    }

    @DeleteMapping("/package/{id}")
    private void delete(@PathVariable int id) {
        packageService.deletePackage(id);
    }
}
