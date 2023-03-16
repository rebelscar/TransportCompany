package com.f100385.transportcompany.vehicle.vehicle_detail;

import com.f100385.transportcompany.other.exception.VehicleDetailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {

    @Autowired
    private VehicleDetailRepository vehicleDetailRepository;

    @Override
    public VehicleDetail add(VehicleDetail detail) {
        return this.vehicleDetailRepository.save(detail);
    }

    @Override
    public void update(int id, VehicleDetail detail) {
        Optional<VehicleDetail> tempDetail = this.vehicleDetailRepository.findById(id);

        if (tempDetail.isPresent()) {
            VehicleDetail validDetail = tempDetail.get();

            validDetail.setModel(detail.getModel());
            validDetail.setManufactured(detail.getManufactured());
            validDetail.setBrand(detail.getBrand());

            this.vehicleDetailRepository.save(validDetail);
        } else {
            throw new VehicleDetailNotFoundException(id);
        }
    }

    @Override
    public void remove(int id) {
        this.vehicleDetailRepository.deleteById(id);
    }

    @Override
    public List<VehicleDetail> getAll() {
        return this.vehicleDetailRepository.findAll();
    }

    @Override
    public VehicleDetail get(int id) {
        Optional<VehicleDetail> info = this.vehicleDetailRepository.findById(id);
        if (info.isPresent()) {
            return info.get();
        } else {
            throw new VehicleDetailNotFoundException(id);
        }
    }


}
