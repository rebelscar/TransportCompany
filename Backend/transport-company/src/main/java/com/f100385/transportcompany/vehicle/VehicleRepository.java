package com.f100385.transportcompany.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    void deleteById(Integer id);
    List<Vehicle> findAllByVehicleDetail_BrandStartingWithIgnoreCase(String brand);
}
