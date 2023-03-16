package com.f100385.transportcompany.client;

import com.f100385.transportcompany.other.exception.ClientNotFoundException;
import com.f100385.transportcompany.transport_company.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of the ClientService interface.
 * Delegates all the CRUD operations to the repository,
 * as it executes custom logic beforehand.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    /**
     * Adds/Saves a new Client.
     *
     * @param client The received client object.
     * @return The client (success) or null (failure).
     */
    @Override
    public Client add(Client client) {
        client.setCompanies(
                Stream.of(transportCompanyRepository.findTransportCompanyById(1)).collect(Collectors.toSet()));

        return this.clientRepository.save(client);
    }

    /**
     * Updates an existing Client.
     *
     * @param id The target Client ID to be changed.
     * @param client The new Client information to be used instead.
     *
     * @return The client (success) or null (failure).
     * @throws ClientNotFoundException If the there is no client
     * with an id matching the passed one.
     */
    @Override
    public Client update(int id, Client client) {
        Optional<Client> tempClient = this.clientRepository.findById(id);

        if (tempClient.isPresent()) {
            Client validClient = tempClient.get();

            validClient.setFirstName(client.getFirstName());
            validClient.setLastName(client.getLastName());
            validClient.setAge(client.getAge());
            validClient.setTransactions(client.getTransactions());
            // We are getting a single company but the relationship is many-to-many
            validClient.setCompanies(
                    Stream.of(transportCompanyRepository.findTransportCompanyById(1)).collect(Collectors.toSet()));

            return this.clientRepository.save(validClient);
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    @Override
    public List<Client> getAll(Optional<String> name) {
        if (name.isPresent() && !name.get().isEmpty()) {
            return this.clientRepository.findAllByFirstNameStartingWithIgnoreCase(name.get());
        }
        return getAll();
    }

    @Override
    public void remove(int id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client get(int id) {
        Optional<Client> client = this.clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ClientNotFoundException(id);
        }
    }
}
