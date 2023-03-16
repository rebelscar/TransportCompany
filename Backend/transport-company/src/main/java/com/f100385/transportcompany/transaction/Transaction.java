package com.f100385.transportcompany.transaction;

import com.f100385.transportcompany.client.Client;
import com.f100385.transportcompany.transport_company.TransportCompany;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "money_to_pay")
    private double moneyToPay;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client_transactions")
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(using = TransactionSerializer.class)
    private Client client;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id")
    @JsonBackReference(value = "company_transactions")
    @JsonIgnore
    private TransportCompany transportCompany;


    public Transaction(int id, double moneyToPay, Client client, TransportCompany transportCompany) {
        this(moneyToPay, client, transportCompany);
        this.id = id;
    }

    public Transaction(double moneyToPay, Client client, TransportCompany transportCompany) {
        this.moneyToPay = moneyToPay;
        this.client = client;
        this.transportCompany = transportCompany;
    }

    protected Transaction() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoneyToPay() {
        return moneyToPay;
    }

    public void setMoneyToPay(double moneyToPay) {
        this.moneyToPay = moneyToPay;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
}

