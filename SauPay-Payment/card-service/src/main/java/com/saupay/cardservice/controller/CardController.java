package com.saupay.cardservice.controller;

import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("v1/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService)
    {
        this.cardService = cardService;
    }

    @PostMapping("/createCard/{userId}/{bankId}")
    public ResponseEntity<CardDto> createCard(@PathVariable String userId, @PathVariable String bankId){
        CardDto cardDto=cardService.createCard(userId,bankId);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping("/getCard/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable String id){
        CardDto cardDto=cardService.findCard(id);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping("/getCardAll")
    public ResponseEntity <List<CardDto>> getCardAll(){
        List<CardDto> cardDto=cardService.findCardAll();
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping("/getCardsByUserId/{userId}")
    public ResponseEntity <List<CardDto>> getCardsByUserId(@PathVariable String userId){
        List<CardDto> cardDto=cardService.findCardsByUserId(userId);
        return ResponseEntity.ok(cardDto);
    }



}
