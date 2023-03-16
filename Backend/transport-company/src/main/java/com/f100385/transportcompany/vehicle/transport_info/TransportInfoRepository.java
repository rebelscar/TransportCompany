package com.f100385.transportcompany.vehicle.transport_info;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportInfoRepository extends JpaRepository<TransportInfo, Integer> {
    void deleteById(Integer id);
    List<TransportInfo> findAllByDestinationStartingWithIgnoreCase(String destination);
}
