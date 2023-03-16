package com.f100385.transportcompany.other.old;

import com.f100385.transportcompany.other.exception.TransportInfoNotFoundException;
import com.f100385.transportcompany.vehicle.transport_info.TransportInfo;
import com.f100385.transportcompany.vehicle.transport_info.TransportInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class TransportInfoServiceImpl implements TransportInfoService {

//    @Autowired
//    private TransportInfoRepository transportInfoRepo;
//
//    @Override
//    public void add(TransportInfo info) {
//        this.transportInfoRepo.save(info);
//    }
//
//    @Override
//    public void update(int id, TransportInfo info) {
//        Optional<TransportInfo> tempInfo = this.transportInfoRepo.findById(id);
//
//        if (tempInfo.isPresent()) {
//            TransportInfo validInfo = tempInfo.get();
//
//            validInfo.setVehicle(info.getVehicle());
//            validInfo.setCost(info.getCost());
//            validInfo.setCargo(info.getCargo());
//            validInfo.setDestination(info.getDestination());
//
//            this.transportInfoRepo.save(validInfo);
//        } else {
//            throw new TransportInfoNotFoundException(id);
//        }
//    }
//
//    @Override
//    public void remove(int id) {
//        this.transportInfoRepo.deleteById(id);
//    }
//
//    @Override
//    public List<TransportInfo> getAll() {
//        return this.transportInfoRepo.findAll();
//    }
//
//    @Override
//    public TransportInfo get(int id) {
//        Optional<TransportInfo> info = this.transportInfoRepo.findById(id);
//        if (info.isPresent()) {
//            return info.get();
//        } else {
//            throw new TransportInfoNotFoundException(id);
//        }
//    }
}
