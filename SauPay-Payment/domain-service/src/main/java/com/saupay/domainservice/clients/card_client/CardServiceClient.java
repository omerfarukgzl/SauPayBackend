package com.saupay.domainservice.clients.card_client;

import com.saupay.domainservice.clients.card_client.request.CreateCardRequest;
import com.saupay.domainservice.clients.request.AddCardRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "card-service" ,path = "v1/card") // --> Bu isim, Eureka Server üzerinde kayıtlı olmalıdır.
// Kayıtlı olan card-service ismi ile kaydolan service ile haberleşecektir . path ise card-service üzerindeki pathdir. Hangi path ile haberleşeceğini belirtir.
public interface CardServiceClient {

    @PostMapping("/createCard")
    ResponseEntity<CardDto> createCard(@RequestBody CreateCardRequest createCardRequest);

    @GetMapping("/getCardByBinNumber/{binNumber}")
    ResponseEntity<CardJoinDto> getCardByBinNumber(@PathVariable Integer binNumber);

    @GetMapping("/getCardByCardNumber/{cardNumber}")
    ResponseEntity<CardDto> getCardByCardNumber(@PathVariable String cardNumber);

    @GetMapping("/getCardsBankByUserId/{userId}")
    ResponseEntity<CardJoinDtoList> getCardsBankByUserId(@PathVariable String userId);

    @GetMapping("/getCardById/{id}")
    ResponseEntity<CardDto> getCardById(@PathVariable String id);

    @GetMapping("/getCardAll")
    ResponseEntity <List<CardDto>> getCardAll();

    @GetMapping("/getCardsByUserId/{userId}")
    ResponseEntity <List<CardDto>> getCardsByUserId(@PathVariable String userId);



}
