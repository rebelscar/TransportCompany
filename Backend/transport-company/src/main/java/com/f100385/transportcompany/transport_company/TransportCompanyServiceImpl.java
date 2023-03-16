package com.f100385.transportcompany.transport_company;

import com.f100385.transportcompany.other.exception.CompanyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    @Override
    public void add(TransportCompany company) {
        this.transportCompanyRepository.save(company);
    }

    @Override
    public void update(int id, TransportCompany company) {
        Optional<TransportCompany> tempCompany = this.transportCompanyRepository.findById(id);

        if (tempCompany.isPresent()) {
            TransportCompany validCompany = tempCompany.get();

            validCompany.setVehicles(company.getVehicles());
            validCompany.setEmployees(company.getEmployees());
            validCompany.setClients(company.getClients());
            validCompany.setTransactions(company.getTransactions());
            validCompany.setName(company.getName());

            this.transportCompanyRepository.save(validCompany);
        } else {
            throw new CompanyNotFoundException(id);
        }
    }

    @Override
    public void remove(int id) {
        this.transportCompanyRepository.deleteById(id);
    }

    @Override
    public List<TransportCompany> getAll() {
        return this.transportCompanyRepository.findAll();
    }

    @Override
    public TransportCompany get(int id) {
        Optional<TransportCompany> company = this.transportCompanyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        } else {
            throw new CompanyNotFoundException(id);
        }
    }

    @Override
    public List<TransportCompanyPartial> getNamesAndIds() {
        return this.transportCompanyRepository.findTransportCompanyPartially();
    }
}
