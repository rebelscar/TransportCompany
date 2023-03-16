package com.f100385.transportcompany.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    void deleteById(int id);
    List<Transaction> findAllByClient_Id(int id);
}
