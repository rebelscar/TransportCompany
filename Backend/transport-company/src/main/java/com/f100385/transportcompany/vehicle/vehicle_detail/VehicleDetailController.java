package com.f100385.transportcompany.vehicle.vehicle_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle_details")
public class VehicleDetailController {

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @GetMapping
    public List<VehicleDetail> all() {
        return vehicleDetailService.getAll();
    }

    @GetMapping("/{id}")
    public VehicleDetail one(@PathVariable int id) {
        return vehicleDetailService.get(id);
    }

    @PostMapping
    public void newVehicleDetail(@RequestBody VehicleDetail detail) {
        vehicleDetailService.add(detail);
    }

    @PutMapping("/{id}")
    public void editVehicleDetail(@PathVariable int id, @RequestBody VehicleDetail newDetail) {
        vehicleDetailService.update(id, newDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleDetail(@PathVariable int id) {
        vehicleDetailService.remove(id);
    }

}
