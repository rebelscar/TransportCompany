package com.f100385.transportcompany.client;

import com.f100385.transportcompany.other.base.Person;
import com.f100385.transportcompany.transaction.Transaction;
import com.f100385.transportcompany.transport_company.TransportCompany;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The class represents a client that has basic information.
 */
@Entity
@Table(name = "client")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Client extends Person {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "client_transactions")
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

//    @JsonIgnore
    @JsonManagedReference(value = "company_clients")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "transport_company_has_client",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "transport_company_id", referencedColumnName = "id")})
    @JsonIgnore
    private Set<TransportCompany> companies;

    public Client(int id, String firstName, String lastName, int age) {
        super(id, firstName, lastName, age);
        initializeTransactions();
    }

    public Client(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        initializeTransactions();
    }

    protected Client() {
        super();
        initializeTransactions();
    }

    private void initializeTransactions() {
        this.transactions = new HashSet<>();
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<TransportCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<TransportCompany> companies) {
        this.companies = companies;
    }
}
