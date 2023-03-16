package com.f100385.transportcompany.transport_company;


import java.util.List;

public interface TransportCompanyService {
    void add(TransportCompany company);
    void update(int id, TransportCompany company);
    void remove(int id);
    List<TransportCompany> getAll();
    TransportCompany get(int id);
    List<TransportCompanyPartial> getNamesAndIds();

}
