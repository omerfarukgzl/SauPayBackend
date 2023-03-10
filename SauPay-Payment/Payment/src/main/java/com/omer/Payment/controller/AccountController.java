package com.omer.Payment.controller;

import com.omer.Payment.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


/*    @PostMapping("/createAccount")
    public String createAccount(){


        return "account/create";
    }*/

    @GetMapping("/{id}")
    public String getAccount(){
        return "account/get";
    }

    @PutMapping("/update/{id}")
    public String updateAccount(){
        return "account/update";
    }

    @PutMapping("/delete/{id}")
    public String deleteAccount(){
        return "account/delete";
    }












}
