package com.saupay.domainservice.controller;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.transaction_client.request.EncryptedPaymentRequest;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantsDto;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionsDto;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.response.Response;
import com.saupay.domainservice.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1/saupay")
public class DomainController {

    private final DomainService domainService;


    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }


    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(domainService.getUser(userId));
    }

    @GetMapping("/getCardsByUser/{userId}")
    public ResponseEntity<List<CardDto>> getCardsUser(@PathVariable(value = "userId") String userId){
        List<CardDto> cards = domainService.getCardsUser(userId);
        return ResponseEntity.ok(cards);
    }
    @GetMapping("/getTransactionByCardId/{cardId}")
    public Response <TransactionsDto> getTransaction(@PathVariable(value = "cardId") String cardId){
        return new Response<>(domainService.getTransactionByCardId(cardId));
    }
    @GetMapping("/getTransactionByUserId/{userId}")
    public Response<TransactionsDto> getTransactionByUserId(@PathVariable(value = "userId") String userId){
        return new Response<>(domainService.getTransactionByUserId(userId));
    }
    @GetMapping("/getTransactionMerchantByUserId/{userId}")
    public Response <Transaction_MerchantsDto> getTransactionMerchantByCardId(@PathVariable(value = "userId") String userId){
        return new Response<>(domainService.getTransaction_MerchantByUserId(userId));
    }

/*    @PostMapping("/generatePaymentToken")
    public ResponseEntity <String> generatePaymentToken(HttpServletRequest httpServletRequest, @RequestBody EncryptedPaymentRequest encryptedPaymentRequest){
        return ResponseEntity.ok(domainService.generatePaymentToken(httpServletRequest,encryptedPaymentRequest));
    }*/
}
