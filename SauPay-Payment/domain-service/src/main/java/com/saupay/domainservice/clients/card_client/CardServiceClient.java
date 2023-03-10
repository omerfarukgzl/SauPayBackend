package com.saupay.domainservice.clients.card_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "card-service" ,path = "v1/card") // --> Bu isim, Eureka Server üzerinde kayıtlı olmalıdır.
// Kayıtlı olan card-service ismi ile kaydolan service ile haberleşecektir . path ise card-service üzerindeki pathdir. Hangi path ile haberleşeceğini belirtir.
public interface CardServiceClient {

    @PostMapping("/createCard/{userId}/{bankId}")
    ResponseEntity<CardDto> createCard(@PathVariable String userId, @PathVariable String bankId);

    @GetMapping("/getCard/{id}")
    ResponseEntity<CardDto> getCard(@PathVariable String id);

    @GetMapping("/getCardAll")
    ResponseEntity <List<CardDto>> getCardAll();

    @GetMapping("/getCardsByUserId/{userId}")
    ResponseEntity <List<CardDto>> getCardsByUserId(@PathVariable String userId);


















}
