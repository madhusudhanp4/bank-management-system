package com.wipro.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.bank.dto.TransactionDto;
import com.wipro.bank.service.ITransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService service;

    //  Deposit
    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return service.deposit(accountNumber, amount);
    }

    //  Withdraw
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return service.withdraw(accountNumber, amount);
    }

    //  Get transaction history
    @GetMapping("/{accountNumber}")
    public List<TransactionDto> getTransactions(@PathVariable String accountNumber) {
        return service.getTransactionsByAccount(accountNumber);
    }
}