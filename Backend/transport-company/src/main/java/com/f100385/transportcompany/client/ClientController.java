package com.f100385.transportcompany.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> all(@RequestParam(name = "name") Optional<String> name) {
        return clientService.getAll(name);
    }

    @GetMapping("/{id}")
    public Client one(@PathVariable int id) {
        return clientService.get(id);
    }

    @PostMapping
    public void newClient(@RequestBody Client client) {
        clientService.add(client);
    }

    @PutMapping("/{id}")
    public void editClient(@PathVariable int id, @RequestBody Client newClient) {
        clientService.update(id, newClient);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.remove(id);
    }
}
