package com.f100385.transportcompany.transport_company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Integer> {
    void deleteById(Integer id);
    TransportCompany findTransportCompanyById(int id);

    @Query(value = "SELECT " +
            "new com.f100385.transportcompany.transport_company.TransportCompanyPartial(t.id, t.name) " +
            "FROM TransportCompany t")
    List<TransportCompanyPartial> findTransportCompanyPartially();
}
