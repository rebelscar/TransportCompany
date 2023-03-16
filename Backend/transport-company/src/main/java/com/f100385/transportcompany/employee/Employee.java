package com.f100385.transportcompany.employee;

import com.f100385.transportcompany.other.base.Person;
import com.f100385.transportcompany.transport_company.TransportCompany;
import com.f100385.transportcompany.vehicle.VehicleQualification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    @Column(name = "qualification")
    @Enumerated(EnumType.STRING)
    private VehicleQualification qualification;

    @Column(name = "salary", nullable = false)
    @Min(0)
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id")
    @JsonIgnore
    private TransportCompany transportCompany;

    public Employee(int id, String firstName, String lastName, int age, double salary) {
        super(id, firstName, lastName, age);
        this.salary = salary;
    }

    public Employee(int id, String firstName, String lastName, int age, double salary, VehicleQualification qualification) {
        super(id, firstName, lastName, age);
        this.salary = salary;
        this.qualification = qualification;
    }

    public Employee(String firstName, String lastName, int age, double salary) {
        super(firstName, lastName, age);
        this.salary = salary;
    }


    public Employee(String firstName, String lastName, int age, double salary, VehicleQualification qualification) {
        super(firstName, lastName, age);
        this.salary = salary;
        this.qualification = qualification;
    }


    protected Employee() {}

    public VehicleQualification getQualification() {
        return qualification;
    }

    public void setQualification(VehicleQualification qualification) {
        this.qualification = qualification;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
}
