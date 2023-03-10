package com.saupay.transactionservice.controller;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/createTransaction/{cardId}/{merchantId}")
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable String cardId,@PathVariable String merchantId){
        transactionService.createTransaction(cardId,merchantId);
        return ResponseEntity.ok(transactionService.createTransaction(cardId,merchantId));
    }

    @GetMapping("/findTransaction/{id}")
    public ResponseEntity<TransactionDto> findTransaction(@PathVariable String id){
        return ResponseEntity.ok(transactionService.findTransaction(id));
    }

    @GetMapping("/findTransactionAll")
    public ResponseEntity <List<TransactionDto>> findTransactionAll(){
        return ResponseEntity.ok(transactionService.findAllTransaction());
    }

    @GetMapping("/findTransactionByCardId/{cardId}")
    public ResponseEntity <List<TransactionDto>> findTransactionByCardId(@PathVariable String cardId){
        return ResponseEntity.ok(transactionService.findTransactionByCardId(cardId));
    }


}
