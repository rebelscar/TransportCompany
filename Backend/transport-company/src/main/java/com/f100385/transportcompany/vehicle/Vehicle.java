package com.f100385.transportcompany.vehicle;

import com.f100385.transportcompany.transport_company.TransportCompany;
import com.f100385.transportcompany.vehicle.transport_info.TransportInfo;
import com.f100385.transportcompany.vehicle.vehicle_detail.VehicleDetail;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonDeserialize(using = VehicleDeserializer.class)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_detail_id", nullable = false)
    private VehicleDetail vehicleDetail;

    @OneToMany(mappedBy = "vehicle", cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH })
    @JsonManagedReference(value = "vehicle_transports")
    @JsonIgnore
    private List<TransportInfo> transports;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id", nullable = false)
    @JsonBackReference(value = "company_vehicles")
    private TransportCompany transportCompany;

    public Vehicle(int id, VehicleDetail vehicleDetail) {
        this.id = id;
        this.vehicleDetail = vehicleDetail;
        initializeTransports();
    }

    public Vehicle(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
        initializeTransports();
    }

    protected Vehicle() {
        initializeTransports();
    }

    private void initializeTransports() {
        this.transports = new ArrayList<>();
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TransportInfo> getTransports() {
        return transports;
    }

    public void setTransports(List<TransportInfo> transports) {
        this.transports = transports;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public void addTransportInfo(TransportInfo transportInfo) {
        if (transports == null) {
            transports = new ArrayList<>();
        }
        transports.add(transportInfo);
    }
}
