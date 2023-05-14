package com.saupay.domainservice.clients.transaction_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "transaction-service" ,path = "v1/transaction")
public interface TransactionServiceClient {

    @PostMapping("/createTransaction/{cardId}/{merchantId}")
    ResponseEntity<TransactionDto> createTransaction(@PathVariable String cardId, @PathVariable String merchantId);

    @GetMapping("/getTransaction/{id}")
    ResponseEntity<TransactionDto> getTransaction(@PathVariable String id);

    @GetMapping("/getTransactionAll")
    ResponseEntity <List<TransactionDto>> getTransactionAll();

    @GetMapping("/getTransactionByCardId/{cardId}")
    ResponseEntity <TransactionsDto> getTransactionByCardId(@PathVariable String cardId);

    @GetMapping("/getTransactionMerchantByCardId/{cardId}")
    ResponseEntity <Transaction_MerchantsDto> getTransactionMerchantByCardId(@PathVariable String cardId);

    @GetMapping("/generatePaymentToken/{request}")
    ResponseEntity <String> generatePaymentToken(@PathVariable String request);
}
