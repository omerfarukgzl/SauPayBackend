package com.omer.Payment.service;

import com.omer.Payment.dto.AccountDto;
import com.omer.Payment.model.Account;
import com.omer.Payment.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class AccountService {


    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account createAccount() {

        Account account = new Account(LocalDateTime.now(),new HashSet<>());
        accountRepository.save(account);
        return account;
    }

    public Account findAccount(String id){
        return accountRepository.findById(id).orElse(null);
    }








}
