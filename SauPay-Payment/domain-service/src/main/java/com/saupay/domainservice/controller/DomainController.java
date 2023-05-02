package com.saupay.domainservice.controller;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.transaction_client.TransactionDto;
import com.saupay.domainservice.clients.transaction_client.Transaction_MerchantDto;
import com.saupay.domainservice.clients.transaction_client.TransactionsDto;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/saupay")
public class DomainController {

    private final DomainService domainService;


    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/getCardsByUser/{userId}")
    ResponseEntity<List<CardDto>> getCardsUser(@PathVariable(value = "userId") String userId){
        List<CardDto> cards = domainService.getCardsUser(userId);
        return ResponseEntity.ok(cards);
    }
    @GetMapping("/getUser/{userId}")
    ResponseEntity<UserDto> getUserInfo(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(domainService.getUser(userId));
    }
    @GetMapping("/getTransactionByCardId/{cardId}")
    ResponseEntity <TransactionsDto> getTransaction(@PathVariable(value = "cardId") String cardId){
        return ResponseEntity.ok(domainService.getTransactionByCardId(cardId));
    }
    @GetMapping("/getTransactionByUserId/{userId}")
    ResponseEntity<TransactionsDto> getTransactionByUserId(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(domainService.getTransactionByUserId(userId));
    }
    @GetMapping("/getTransactionMerchantByUserId/{userId}")
    ResponseEntity<List<Transaction_MerchantDto>> getTransactionMerchantByCardId(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(domainService.getTransaction_MerchantByUserId(userId));
    }
}
