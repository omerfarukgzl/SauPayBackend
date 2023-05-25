package com.saupay.domainservice.controller;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardJoinDto;
import com.saupay.domainservice.clients.card_client.CardJoinDtoList;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantDto;
import com.saupay.domainservice.clients.request.EncryptedPaymentRequest;
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

    @GetMapping("/getCardsByUserId/{userId}")
    public ResponseEntity<List<CardDto>> getCardsByUserId(@PathVariable(value = "userId") String userId){
        List<CardDto> cards = domainService.getCardsUser(userId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/getCardByBinNumber/{binNumber}")
    public Response<CardJoinDto> getCardByBinNumber(@PathVariable(value = "binNumber") Integer binNumber){
        CardJoinDto cards = domainService.getCardByBinNumber(binNumber);
        return new Response<>(cards);
    }

    @GetMapping("/getCardsBankByUserEmail")
    public ResponseEntity<CardJoinDtoList> getCardsBankByUserEmail(HttpServletRequest httpServletRequest,@RequestBody EncryptedPaymentRequest encryptedPaymentRequest){
        System.out.println("encryptedPaymentCardRequest: " + encryptedPaymentRequest);

        String signature = httpServletRequest.getHeader("x-signature");
        String randomKey = httpServletRequest.getHeader("x-rnd-key");
        System.out.println("Card-signature: " + signature);
        System.out.println("Card-randomKey: " + randomKey);

        CardJoinDtoList cardDto=domainService.getCardsBankByUserEmail(encryptedPaymentRequest,signature,randomKey);
        return ResponseEntity.ok(cardDto);
    }

/*    @GetMapping("/getCardsBankByUserId/{userId}")
    public ResponseEntity<CardJoinDtoList> getCardsBankByUserId(@PathVariable String userId){
        CardJoinDtoList cardDto=domainService.getCardsBankByUserId(userId);
        return ResponseEntity.ok(cardDto);
    }*/


    @GetMapping("/getTransactionByCardId/{cardId}")
    public Response <TransactionsDto> getTransaction(@PathVariable(value = "cardId") String cardId){
        return new Response<>(domainService.getTransactionByCardId(cardId));
    }
    @GetMapping("/getTransactionByUserId/{userId}")
    public Response<TransactionsDto> getTransactionByUserId(@PathVariable(value = "userId") String userId){
        return new Response<>(domainService.getTransactionByUserId(userId));
    }
    @GetMapping("/getTransactionMerchantByUserId/{userId}")
    public Response <Transaction_MerchantsDto> getTransactionMerchantByUserId(@PathVariable(value = "userId") String userId){
        return new Response<>(domainService.getTransaction_MerchantByUserId(userId));
    }

    @PostMapping("/getTransactionMerchantByToken")
    public Response <Transaction_MerchantDto> getTransactionMerchantByToken(HttpServletRequest httpServletRequest, @RequestBody EncryptedPaymentRequest encryptedPaymentRequest){

        System.out.println("encryptedPaymentRequest: " + encryptedPaymentRequest);

        String signature = httpServletRequest.getHeader("x-signature");
        String randomKey = httpServletRequest.getHeader("x-rnd-key");
        System.out.println("signature: " + signature);
        System.out.println("randomKey: " + randomKey);
        return new Response<>(domainService.getTransaction_MerchantByToken(signature,randomKey,encryptedPaymentRequest));
    }














/*    @PostMapping("/generatePaymentToken")
    public ResponseEntity <String> generatePaymentToken(HttpServletRequest httpServletRequest, @RequestBody EncryptedPaymentRequest encryptedPaymentRequest){
        return ResponseEntity.ok(domainService.generatePaymentToken(httpServletRequest,encryptedPaymentRequest));
    }*/
}
