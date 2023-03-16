package com.f100385.transportcompany.transport_company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class TransportCompanyController {

    @Autowired
    private TransportCompanyService transportCompanyService;

    @GetMapping
    public List<TransportCompany> all() {
        return transportCompanyService.getAll();
    }

    @GetMapping("/{id}")
    public TransportCompany one(@PathVariable int id) {
        return transportCompanyService.get(id);
    }

    @GetMapping("/names")
    public List<TransportCompanyPartial> names() {
        return transportCompanyService.getNamesAndIds();
    }

    @PostMapping
    public void newTransportCompany(@RequestBody TransportCompany company) {
        transportCompanyService.add(company);
    }

    @PutMapping("/{id}")
    public void editTransportCompany(@PathVariable int id, @RequestBody TransportCompany newTransportCompany) {
        transportCompanyService.update(id, newTransportCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteTransportCompany(@PathVariable int id) {
        transportCompanyService.remove(id);
    }

}
