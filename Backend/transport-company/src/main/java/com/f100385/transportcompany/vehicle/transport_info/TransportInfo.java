package com.f100385.transportcompany.vehicle.transport_info;

import com.f100385.transportcompany.transaction.TransactionSerializer;
import com.f100385.transportcompany.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity
@Table(name = "transport_info")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonDeserialize(using = TransportInfoDeserializer.class)
public class TransportInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "destination")
    private String destination;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "cost")
    private double cost;

//    @ManyToOne
//    @JoinColumn(name = "vehicle_id")
//    @JsonManagedReference(value = "vehicle_transports")
//    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference(value = "vehicle_transports")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(using = TransportInfoSerializer.class)
    private Vehicle vehicle;

    public TransportInfo(int id, String destination, String cargo, double cost, Vehicle vehicle) {
        this.id = id;
        this.destination = destination;
        this.cargo = cargo;
        this.cost = cost;
        this.vehicle = vehicle;
    }

    public TransportInfo(String destination, String cargo, double cost, Vehicle vehicle) {
        this.destination = destination;
        this.cargo = cargo;
        this.cost = cost;
        this.vehicle = vehicle;
    }

    protected TransportInfo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
