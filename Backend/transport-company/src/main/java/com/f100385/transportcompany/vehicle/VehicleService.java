package com.f100385.transportcompany.vehicle;


import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle add(Vehicle vehicle);
    void update(int id, Vehicle vehicle);
    void remove(int id);
    List<Vehicle> getAll();
    Vehicle get(int id);
    List<Vehicle> getAll(Optional<String> vehicle);
}
