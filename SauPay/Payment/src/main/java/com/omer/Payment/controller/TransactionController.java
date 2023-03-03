package com.omer.Payment.controller;

import com.omer.Payment.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/createTransaction/{cardId}/{CommerceId}")
    public ResponseEntity<Boolean> createTransaction(@PathVariable String cardId,@PathVariable String CommerceId){
        transactionService.createTransaction(cardId,CommerceId);
        return ResponseEntity.ok(true);
    }


}
