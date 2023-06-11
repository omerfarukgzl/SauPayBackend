package com.saupay.domainservice.clients.transaction_client;

import com.saupay.domainservice.clients.request.BankRequest;
import com.saupay.domainservice.clients.request.EncryptedPaymentRequest;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionDto;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantDto;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantsDto;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionsDto;
import com.saupay.domainservice.response.PaymentBankResponse;
import com.saupay.domainservice.response.TreeDSecureResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(name = "transaction-service" ,path = "v1/transaction")
public interface TransactionServiceClient {

    @GetMapping("/getTransaction/{id}")
    ResponseEntity<TransactionDto> getTransaction(@PathVariable String id);

    @PutMapping("/updateTransaction")
    ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction);


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

    @GetMapping("/getTransactionByToken/{token}")
    ResponseEntity<Transaction> getTransactionByToken(@PathVariable String token);

    @GetMapping("/getTreeDSecureResponse/{token}")
    ResponseEntity<TreeDSecureResponse> getTreeDSecureResponse(@PathVariable String token);

    @PostMapping("/paymentBank")
    ResponseEntity<PaymentBankResponse> paymentBank(@RequestBody String decryptedData);

}