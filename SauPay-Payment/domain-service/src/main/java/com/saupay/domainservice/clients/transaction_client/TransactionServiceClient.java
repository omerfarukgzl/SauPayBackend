package com.saupay.domainservice.clients.transaction_client;

import com.saupay.domainservice.clients.transaction_client.dto.TransactionDto;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantDto;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantsDto;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(name = "transaction-service" ,path = "v1/transaction")
public interface TransactionServiceClient {

    @GetMapping("/getTransaction/{id}")
    ResponseEntity<TransactionDto> getTransaction(@PathVariable String id);

    @GetMapping("/getTransactionAll")
    ResponseEntity<List<TransactionDto>> getTransactionAll();


    @GetMapping("/getTransactionByCardId/{cardId}")
    ResponseEntity<TransactionsDto> getTransactionByCardId(@PathVariable String cardId);


/*    @GetMapping("/getTransactionMerchantByCardId/{cardId}")
     ResponseEntity<Transaction_MerchantsDto> getTransactionMerchantByCardId(@PathVariable String cardId);*/

    @GetMapping("/getTransactionsMerchantByCardId/{cardId}")
    ResponseEntity<Transaction_MerchantsDto> getTransactionsMerchantByCardId(@PathVariable String cardId);


    @GetMapping("/getTransactionMerchantByToken/{token}")
    ResponseEntity<Transaction_MerchantDto> getTransactionMerchantByToken(@PathVariable String token);

}