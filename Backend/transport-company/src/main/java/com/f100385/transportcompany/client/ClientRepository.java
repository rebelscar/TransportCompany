package com.f100385.transportcompany.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    void deleteById(Integer id);
    List<Client> findAllByFirstNameStartingWithIgnoreCase(String name);
}
