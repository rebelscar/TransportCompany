package com.f100385.transportcompany.transport_company;

import com.f100385.transportcompany.client.Client;
import com.f100385.transportcompany.employee.Employee;
import com.f100385.transportcompany.transaction.Transaction;
import com.f100385.transportcompany.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transport_company")
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "varchar(255) default 'Collins'")
    private String name;

    @OneToMany(mappedBy = "transportCompany")
    @JsonManagedReference(value = "company_transactions")
    @JsonIgnore
    private Set<Transaction> transactions;

    @OneToMany
    @JoinColumn(name = "transport_company_id")
    @JsonManagedReference(value = "company_vehicles")
    private Set<Vehicle> vehicles;

    @OneToMany
    @JoinColumn(name = "transport_company_id")
    private Set<Employee> employees;

    @JsonBackReference(value = "company_clients")
    @ManyToMany(mappedBy = "companies", fetch = FetchType.LAZY)
    private Set<Client> clients;

    public TransportCompany(int id, String name, Set<Transaction> transactions, Set<Vehicle> vehicles, Set<Employee> employees, Set<Client> clients) {
        this.id = id;
        this.name = name;
        this.transactions = transactions;
        this.vehicles = vehicles;
        this.employees = employees;
        this.clients = clients;
    }

    public TransportCompany(int id, String name) {
        this(name);
        this.id = id;
    }

    public TransportCompany(String name) {
        this();
        this.name = name;
    }

    protected TransportCompany() {
        this.transactions = new HashSet<>();
        this.vehicles = new HashSet<>();
        this.employees = new HashSet<>();
        this.clients = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
