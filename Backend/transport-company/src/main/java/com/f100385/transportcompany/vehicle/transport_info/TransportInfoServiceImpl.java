package com.f100385.transportcompany.vehicle.transport_info;

import com.f100385.transportcompany.other.exception.TransportInfoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportInfoServiceImpl implements TransportInfoService {

    @Autowired
    private TransportInfoRepository transportInfoRepository;

    @Override
    public void add(TransportInfo info) {
        this.transportInfoRepository.save(info);
    }

    @Override
    public void update(int id, TransportInfo info) {
        Optional<TransportInfo> tempInfo = this.transportInfoRepository.findById(id);

        if (tempInfo.isPresent()) {
            TransportInfo validInfo = tempInfo.get();

            validInfo.setVehicle(info.getVehicle());
            validInfo.setCost(info.getCost());
            validInfo.setCargo(info.getCargo());
            validInfo.setDestination(info.getDestination());

            this.transportInfoRepository.save(validInfo);
        } else {
            throw new TransportInfoNotFoundException(id);
        }
    }

    @Override
    public void remove(int id) {
        this.transportInfoRepository.deleteById(id);
    }

    @Override
    public List<TransportInfo> getAll() {
        return this.transportInfoRepository.findAll();
    }

    @Override
    public List<TransportInfo> getAll(Optional<String> destination) {
        if (destination.isPresent() && !destination.get().isEmpty()) {
            return this.transportInfoRepository.findAllByDestinationStartingWithIgnoreCase(destination.get());
        }
        return getAll();
    }

    @Override
    public TransportInfo get(int id) {
        Optional<TransportInfo> info = this.transportInfoRepository.findById(id);
        if (info.isPresent()) {
            return info.get();
        } else {
            throw new TransportInfoNotFoundException(id);
        }
    }
}
