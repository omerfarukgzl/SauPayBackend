package com.saupay.domainservice.controller;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/v1/domain")
public class DomainController {

    private final DomainService domainService;


    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/getcards_user/{userId}")
    ResponseEntity<List<CardDto>> getCardsUser(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(domainService.getCardsUser(userId));
    }
}
