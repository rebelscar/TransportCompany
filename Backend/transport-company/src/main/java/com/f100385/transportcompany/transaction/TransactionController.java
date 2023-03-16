package com.f100385.transportcompany.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> all(@RequestParam(name = "id") Optional<String> id) {
        return transactionService.getAll(id);
    }

    @GetMapping("/{id}")
    public Transaction one(@PathVariable int id) {
        return transactionService.get(id);
    }

    @PostMapping
    public void newTransaction(@RequestBody Transaction transaction) {
        transactionService.add(transaction);
    }

    @PutMapping("/{id}")
    public void editTransaction(@PathVariable int id, @RequestBody Transaction newTransaction) {
        transactionService.update(id, newTransaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable int id) {
        transactionService.remove(id);
    }
}
