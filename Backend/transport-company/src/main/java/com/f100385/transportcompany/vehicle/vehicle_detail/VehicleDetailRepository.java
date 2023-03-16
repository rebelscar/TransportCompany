package com.f100385.transportcompany.vehicle.vehicle_detail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailRepository extends JpaRepository<VehicleDetail, Integer> {
    void deleteById(Integer id);
}
