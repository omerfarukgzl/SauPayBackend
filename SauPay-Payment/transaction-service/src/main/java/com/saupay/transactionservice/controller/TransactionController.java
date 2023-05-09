package com.saupay.transactionservice.controller;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
/*    @PostMapping("/createTransaction/{cardId}/{merchantId}")
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable String cardId,@PathVariable String merchantId){
        transactionService.createTransaction(cardId,merchantId);
        return ResponseEntity.ok(transactionService.createTransaction(cardId,merchantId));
    }*/
    @GetMapping("/getTransaction/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable String id){
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }
    @GetMapping("/getTransactionAll")
    public ResponseEntity <List<TransactionDto>> getTransactionAll(){
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
    @GetMapping("/getTransactionByCardId/{cardId}")
    public ResponseEntity <TransactionsDto> getTransactionByCardId(@PathVariable String cardId){
        return ResponseEntity.ok(transactionService.getTransactionByCardId(cardId));
    }
    @GetMapping("/getTransactionMerchantByCardId/{cardId}")
    public ResponseEntity <Transaction_MerchantsDto> getTransactionMerchantByCardId(@PathVariable String cardId){
        return ResponseEntity.ok(transactionService.getTransactionMerchantByCardId(cardId));
    }
    @GetMapping("/generatePaymentToken/{request}")
    public ResponseEntity <String> generatePaymentToken(@PathVariable String request){
        return ResponseEntity.ok(transactionService.generatePaymentToken(request));
    }


}
