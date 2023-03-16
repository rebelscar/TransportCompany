package com.f100385.transportcompany.vehicle.vehicle_detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_detail")
@JsonIgnoreProperties(value = {"vehicle"})
public class VehicleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "manufactured")
    @Temporal(TemporalType.DATE)
    private Date manufactured;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    // Constructors --> START
    public VehicleDetail(int id, Date manufactured, String brand, String model) {
        this.id = id;
        this.manufactured = manufactured;
        this.brand = brand;
        this.model = model;
    }

    public VehicleDetail(Date manufactured, String brand, String model) {
        this.manufactured = manufactured;
        this.brand = brand;
        this.model = model;
    }

    protected VehicleDetail() {}

    // Constructors --> END


    // Getters, setters, toString --> START
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getManufactured() {
        return manufactured;
    }

    public void setManufactured(Date manufactured) {
        this.manufactured = manufactured;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", manufactured=" + manufactured +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    // Getters, setters, toString --> END
}
