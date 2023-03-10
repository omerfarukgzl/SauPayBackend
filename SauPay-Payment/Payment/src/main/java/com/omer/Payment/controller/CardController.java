package com.omer.Payment.controller;

import com.omer.Payment.dto.CardDto;
import com.omer.Payment.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CardController {

    private final CardService cardService;


    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/createCard/{userId}/{bankId}")
    public ResponseEntity<CardDto> createCard(@PathVariable String userId, @PathVariable String bankId){
        CardDto cardDto=cardService.createCard(userId,bankId);
        return ResponseEntity.ok(cardDto);
    }


}
