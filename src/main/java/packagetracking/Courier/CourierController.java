package packagetracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourierController {

    @Autowired
    public CourierService courierService;

    @PostMapping("/courier")
    public Courier addCourier(@RequestBody Courier courier) {
        return courierService.createCourier(courier);
    }

    @GetMapping("/courier")
    public List<Courier> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    @PutMapping("/courier/{id}")
    public Courier updateCourier(@PathVariable int id, @RequestBody Courier courier) {
        return courierService.updateCourier(id, courier);
    }

    @DeleteMapping("/courier/{id}")
    public void deleteCourier(@PathVariable int id) {
        courierService.deleteCourier(id);

    }

}
