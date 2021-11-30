package co.misiontic.p53.account_ms.controllers;

import co.misiontic.p53.account_ms.exceptions.AccountNotFoundException;
import co.misiontic.p53.account_ms.models.Account;
import co.misiontic.p53.account_ms.repositories.AccountRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username) {
        return accountRepository.findById(username).orElseThrow(
                () -> new AccountNotFoundException("No se encontro una cuenta con el username: " + username));
    }

    @GetMapping("/getAllAccounts")
    List<Account> getAllAccounts() {
        return accountRepository.findAll(Sort.by(Sort.Direction.DESC, "balance"));
    }

    @GetMapping("/accountByBalance/{balance}")
    List<Account> getAccountByBalance(@PathVariable Integer balance) {
        return accountRepository.findAllByBalance(balance);
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

}
