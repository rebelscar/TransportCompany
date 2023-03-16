package com.f100385.transportcompany.client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client add(Client client);
    Client update(int id, Client client);
    void remove(int id);
    List<Client> getAll();
    Client get(int id);
    List<Client> getAll(Optional<String> name);
}
