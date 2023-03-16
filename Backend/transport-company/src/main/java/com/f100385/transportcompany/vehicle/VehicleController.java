package com.f100385.transportcompany.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> all(@RequestParam(name = "brand") Optional<String> brand) {
        return vehicleService.getAll(brand);
    }

    @GetMapping("/{id}")
    public Vehicle one(@PathVariable int id) {
        return vehicleService.get(id);
    }

    @PostMapping
    public void newVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.add(vehicle);
    }

    @PutMapping("/{id}")
    public void editVehicle(@PathVariable int id, @RequestBody Vehicle newVehicle) {
        vehicleService.update(id, newVehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleService.remove(id);
    }

}
