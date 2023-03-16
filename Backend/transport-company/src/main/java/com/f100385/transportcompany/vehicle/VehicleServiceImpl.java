package com.f100385.transportcompany.vehicle;

import com.f100385.transportcompany.other.exception.VehicleNotFoundException;
import com.f100385.transportcompany.transport_company.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    @Override
    public Vehicle add(Vehicle vehicle) {
        vehicle.setTransportCompany(transportCompanyRepository.findTransportCompanyById(1));
        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public void update(int id, Vehicle vehicle) {
        Optional<Vehicle> tempVehicle = this.vehicleRepository.findById(id);

        if (tempVehicle.isPresent()) {
            Vehicle validVehicle = tempVehicle.get();

            validVehicle.setVehicleDetail(vehicle.getVehicleDetail());
            validVehicle.setTransports(vehicle.getTransports());
            validVehicle.setTransportCompany(transportCompanyRepository.findTransportCompanyById(1));

            this.vehicleRepository.save(validVehicle);
        } else {
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public void remove(int id) {
        this.vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> getAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getAll(Optional<String> brand) {
        if (brand.isPresent() && !brand.get().isEmpty()) {
            return this.vehicleRepository.findAllByVehicleDetail_BrandStartingWithIgnoreCase(brand.get());
        }
        return getAll();
    }

    @Override
    public Vehicle get(int id) {
        Optional<Vehicle> vehicle = this.vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new VehicleNotFoundException(id);
        }
    }
}
