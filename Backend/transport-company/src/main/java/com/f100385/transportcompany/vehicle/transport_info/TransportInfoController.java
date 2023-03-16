package com.f100385.transportcompany.vehicle.transport_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transport_info")
public class TransportInfoController {

    @Autowired
    private TransportInfoService transportInfoService;

    @GetMapping
    public List<TransportInfo> all(@RequestParam(name = "destination") Optional<String> destination) {
        return transportInfoService.getAll(destination);
    }

    @GetMapping("/{id}")
    public TransportInfo one(@PathVariable int id) {
        return transportInfoService.get(id);
    }

    @PostMapping
    public void newTransportInfo(@RequestBody TransportInfo transportInfo) {
        transportInfoService.add(transportInfo);
    }

    @PutMapping("/{id}")
    public void editTransportInfo(@PathVariable int id, @RequestBody TransportInfo newTransportInfo) {
        transportInfoService.update(id, newTransportInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteTransportInfo(@PathVariable int id) {
        transportInfoService.remove(id);
    }
}
