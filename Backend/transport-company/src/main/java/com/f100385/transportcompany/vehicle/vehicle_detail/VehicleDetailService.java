package com.f100385.transportcompany.vehicle.vehicle_detail;


import java.util.List;

public interface VehicleDetailService {
    VehicleDetail add(VehicleDetail detail);
    void update(int id, VehicleDetail detail);
    void remove(int id);
    List<VehicleDetail> getAll();
    VehicleDetail get(int id);
}
