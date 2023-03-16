package com.f100385.transportcompany.vehicle.transport_info;

import java.util.List;
import java.util.Optional;

public interface TransportInfoService {
    void add(TransportInfo info);
    void update(int id, TransportInfo info);
    void remove(int id);
    List<TransportInfo> getAll();
    TransportInfo get(int id);
    List<TransportInfo> getAll(Optional<String> destination);
}
