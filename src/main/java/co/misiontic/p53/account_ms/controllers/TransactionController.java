package co.misiontic.p53.account_ms.controllers;

import co.misiontic.p53.account_ms.exceptions.AccountNotFoundException;
import co.misiontic.p53.account_ms.exceptions.InsufficientBalanceException;
import co.misiontic.p53.account_ms.models.Account;
import co.misiontic.p53.account_ms.models.Transaction;
import co.misiontic.p53.account_ms.repositories.AccountRepository;
import co.misiontic.p53.account_ms.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class TransactionController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction){
        Account accountOrigin =
                accountRepository.findById(transaction.getUsernameOrigin()).orElse(null);
        Account accountDestinity=
                accountRepository.findById(transaction.getUsernameDestiny()).orElse(null);
        if (accountOrigin == null)
            throw new AccountNotFoundException("No se encontro una cuenta con el username: " + transaction.getUsernameOrigin());
        if (accountDestinity == null)
            throw new AccountNotFoundException("No se encontro una cuenta con el username: " + transaction.getUsernameDestiny());
        if(accountOrigin.getBalance() < transaction.getValue())
            throw new InsufficientBalanceException("Saldo Insuficiente");
        accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        accountOrigin.setLastChange(new Date());
        accountRepository.save(accountOrigin);
        accountDestinity.setBalance(accountDestinity.getBalance() +
                transaction.getValue());
        accountDestinity.setLastChange(new Date());
        accountRepository.save(accountDestinity);
        transaction.setDate(new Date());
        return transactionRepository.save(transaction);
    }
    @GetMapping("/transactions/{username}")
    List<Transaction> userTransaction(@PathVariable String username){
        Account userAccount = accountRepository.findById(username).orElse(null);
        if (userAccount == null)
            throw new AccountNotFoundException("No se encontro una cuenta con el username: " + username);
        List<Transaction> transactionsOrigin =
                transactionRepository.findByUsernameOrigin(username);
        List<Transaction> transactionsDestinity =
                transactionRepository.findByUsernameDestiny(username);
        return Stream.concat(transactionsOrigin.stream(),
                transactionsDestinity.stream()).collect(Collectors.toList());
    }
}
