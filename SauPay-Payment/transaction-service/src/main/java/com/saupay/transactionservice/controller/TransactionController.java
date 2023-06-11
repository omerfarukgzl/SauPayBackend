package com.saupay.transactionservice.controller;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.model.Transaction;
import com.saupay.transactionservice.request.BankRequest;
import com.saupay.transactionservice.request.EncryptedPaymentRequest;
import com.saupay.transactionservice.response.PaymentBankResponse;
import com.saupay.transactionservice.response.TreeDSecureResponse;
import com.saupay.transactionservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PutMapping("/updateTransaction")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction){
        System.out.println("transaction = "
                + transaction.getId()
                + " " + transaction.getCardId()
                + " " + transaction.getMerchantId()
                + " " + transaction.getAmount()
                + " " + transaction.getUserId()
                + " " + transaction.getToken()
                + " " + transaction.getStatus());
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @GetMapping("/getTransactionAll")
    public ResponseEntity <List<TransactionDto>> getTransactionAll(){
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
    @GetMapping("/getTransactionByCardId/{cardId}")
    public ResponseEntity <TransactionsDto> getTransactionByCardId(@PathVariable String cardId){
        return ResponseEntity.ok(transactionService.getTransactionByCardId(cardId));
    }
    @GetMapping("/getTransactionsMerchantByCardId/{cardId}")
    ResponseEntity<Transaction_MerchantsDto> getTransactionsMerchantByCardId(@PathVariable String cardId) {
        return ResponseEntity.ok(transactionService.getTransactionMerchantByCardId(cardId));
    }
    @GetMapping("/getTransactionMerchantByToken/{token}")
    public ResponseEntity<Transaction_MerchantDto> getTransactionMerchantByToken(@PathVariable String token/*HttpServletRequest request,@RequestBody EncryptedPaymentRequest encryptedPaymentRequest*/){
        return ResponseEntity.ok(transactionService.getTransactionMerchantByToken(token/*encryptedPaymentRequest,signature,randomKey*/));
    }

    @GetMapping("/getTransactionByToken/{token}")
    public ResponseEntity<Transaction> getTransactionByToken(@PathVariable String token){
        return ResponseEntity.ok(transactionService.getTransactionByToken(token));
    }

    @PostMapping("/generatePaymentToken")
    public ResponseEntity <String> generatePaymentToken(HttpServletRequest request,@RequestBody EncryptedPaymentRequest encryptedPaymentRequest){
        String signature = request.getHeader("x-signature");
        String randomKey = request.getHeader("x-rnd-key");
        return ResponseEntity.ok(transactionService.generatePaymentToken(encryptedPaymentRequest,signature,randomKey));
    }

    @GetMapping("/getTreeDSecureResponse/{token}")
    ResponseEntity<TreeDSecureResponse> getTreeDSecureResponse(@PathVariable String token){
        return ResponseEntity.ok(transactionService.getTreeDSecureResponse(token));
    }

    @PostMapping("/paymentBank")
    public ResponseEntity<PaymentBankResponse> paymentBank(@RequestBody String decryptedData){
/*        String signature = request.getHeader("x-signature");
        String randomKey = request.getHeader("x-rnd-key");*/
        return ResponseEntity.ok(transactionService.paymentBank(decryptedData));
    }




}


/*    @GetMapping("/getTransactionMerchantByCardId/{cardId}")
    public ResponseEntity <Transaction_MerchantsDto> getTransactionMerchantByCardId(@PathVariable String cardId){
        return ResponseEntity.ok(transactionService.getTransactionMerchantByCardId(cardId));
    }*/

/*    @GetMapping("/generatePaymentToken/{signature}/{randomKey}/{request}")
    public ResponseEntity <String> generatePaymentToken(@PathVariable String encyrptionRequest,@PathVariable String randomKey,@PathVariable String request){
        return ResponseEntity.ok(transactionService.generatePaymentToken(encyrptionRequest,randomKey,request));
    }*/

/*    @PostMapping("/generatePaymentToken")
    public ResponseEntity <String> generatePaymentToken(HttpServletRequest request, @RequestBody EncryptedPaymentRequest encryptedPaymentRequest){

        String signature = request.getHeader("x-signature");
        String randomKey = request.getHeader("x-rnd-key");


        return ResponseEntity.ok(transactionService.generatePaymentToken(encryptedPaymentRequest,signature,randomKey));
    }*/

