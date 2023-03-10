package com.saupay.cardservice.service;
import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.dto.converter.CardDtoConverter;
import com.saupay.cardservice.exception.CardNotFoundException;
import com.saupay.cardservice.model.Bank;
import com.saupay.cardservice.model.Card;
import com.saupay.cardservice.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final BankService bankService;
    private final CardDtoConverter cardDtoConverter;


    public CardService(CardRepository cardRepository, BankService bankService, CardDtoConverter cardDtoConverter) {
        this.cardRepository = cardRepository;
        this.bankService = bankService;
        this.cardDtoConverter = cardDtoConverter;
    }

    public CardDto createCard(String userId, String bankId) {
        //Account account = accountService.findAccount(id);
        Bank bank = bankService.getBank(bankId);
        Card card = new Card("123456789","1234","1234","1234","1234","1234",userId,bank);
        return cardDtoConverter.convert(cardRepository.save(card));
    }

    public CardDto findCard(String id){
        return cardDtoConverter.convert(cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card could not found by id: " + id)));
    }

    public List<CardDto> findCardAll(){
        try {
            return cardRepository.findAll().
                    stream().
                    map(cardDtoConverter::convert).
                    collect(Collectors.toList());
        }
        catch (Exception e){
            throw new CardNotFoundException("Cards could not found");
        }
    }

    public List<CardDto> findCardByUserId(String userId){
        try {
            return cardRepository.findByUserId(userId).
                    stream().
                    map(cardDtoConverter::convert).
                    collect(Collectors.toList());
        }
        catch (Exception e){
            throw new CardNotFoundException("Cards could not found by userId: " + userId);
        }

    }



}
