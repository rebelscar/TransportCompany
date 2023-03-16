package com.f100385.transportcompany.transaction;

import com.f100385.transportcompany.other.exception.TransactionNotFoundException;
import com.f100385.transportcompany.transport_company.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    @Override
    public void add(Transaction transaction) {
        transaction.setTransportCompany(this.transportCompanyRepository.findTransportCompanyById(1));
        this.transactionRepository.save(transaction);
    }

    @Override
    public void update(int id, Transaction transaction) {
        Optional<Transaction> tempTransaction = this.transactionRepository.findById(id);

        if (tempTransaction.isPresent()) {
            Transaction validTransaction = tempTransaction.get();

            validTransaction.setClient(transaction.getClient());
            validTransaction.setMoneyToPay(transaction.getMoneyToPay());
            validTransaction.setTransportCompany(this.transportCompanyRepository.findTransportCompanyById(1));

            this.transactionRepository.save(validTransaction);
        } else {
            throw new TransactionNotFoundException(id);
        }
    }

    @Override
    public void remove(int id) {
        this.transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> getAll() {
        return this.transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAll(Optional<String> id) {
        int clientId;

        if(id.isPresent() && !id.get().isEmpty()) {
            try {
                clientId = Integer.parseInt(id.get());
                return this.transactionRepository.findAllByClient_Id(clientId);
            } catch (NumberFormatException e) {
                return new ArrayList<>();
            }
        }
        return getAll();
    }

    @Override
    public Transaction get(int id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new TransactionNotFoundException(id);
        }
    }
}
