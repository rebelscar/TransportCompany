package com.f100385.transportcompany.transaction;


import java.util.List;
import java.util.Optional;

public interface TransactionService {
    void add(Transaction transaction);
    void update(int id, Transaction transaction);
    void remove(int id);
    List<Transaction> getAll();
    Transaction get(int id);
    List<Transaction> getAll(Optional<String> id);
}
