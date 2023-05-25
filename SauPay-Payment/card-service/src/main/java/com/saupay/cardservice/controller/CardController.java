package com.saupay.cardservice.controller;

import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.dto.CardJoinDto;
import com.saupay.cardservice.dto.CardJoinDtoList;
import com.saupay.cardservice.request.CreateCardRequest;
import com.saupay.cardservice.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService)
    {
        this.cardService = cardService;
    }

    @PostMapping("/createCard")
    public ResponseEntity<CardDto> createCard(@RequestBody CreateCardRequest createCardRequest){
        CardDto cardDto=cardService.createCard(createCardRequest);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping("/getCardByBinNumber/{binNumber}")
    public ResponseEntity<CardJoinDto> getCardByBinNumber(@PathVariable Integer binNumber){
        CardJoinDto cardDto=cardService.findCardByBinNumber(binNumber);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping("/getCardsBankByUserId/{userId}")
    public ResponseEntity<CardJoinDtoList> getCardsBankByUserId(@PathVariable String userId){
        CardJoinDtoList cardDto=cardService.findCardsBankByUserId(userId);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping("/getCardById/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable String id){
        CardDto cardDto=cardService.findCardById(id);
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
