package com.saupay.cardservice.service;
import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.dto.CardJoinDto;
import com.saupay.cardservice.dto.CardJoinDtoList;
import com.saupay.cardservice.dto.converter.CardDtoConverter;
import com.saupay.cardservice.dto.converter.CardDtoJoinConverter;
import com.saupay.cardservice.exception.CardNotFoundException;
import com.saupay.cardservice.model.Bank;
import com.saupay.cardservice.model.BankCard;
import com.saupay.cardservice.model.Card;
import com.saupay.cardservice.repository.BankCardRepository;
import com.saupay.cardservice.repository.BankRepository;
import com.saupay.cardservice.repository.CardRepository;
import com.saupay.cardservice.request.CreateCardRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final BankCardRepository bankCardRepository;


    private final BankRepository bankRepository;
    private final CardDtoConverter cardDtoConverter;




    public CardService(CardRepository cardRepository, BankCardRepository bankCardRepository, BankRepository bankRepository, CardDtoConverter cardDtoConverter ) {
        this.cardRepository = cardRepository;
        this.bankCardRepository = bankCardRepository;
        this.bankRepository = bankRepository;
        this.cardDtoConverter = cardDtoConverter;
    }

    public CardDto createCard(CreateCardRequest createCardRequest) {
        System.out.println("Create Card Service");
        BankCard bankCard = bankCardRepository.findByBinNumber(createCardRequest.getBinNumber()).orElseThrow(() -> new CardNotFoundException("Bank card could not found by bin number: " + createCardRequest.getBinNumber()));
        Bank bank = bankRepository.findByBankCode(bankCard.getBankCode()).orElseThrow(() -> new CardNotFoundException("Bank could not found by bank code: " + bankCard.getBankCode()));
        Card card = new Card(createCardRequest.getCardNumber(), bankCard.getBinNumber(), createCardRequest.getCardHolderName(),
                createCardRequest.getCardCvv(), createCardRequest.getCardExpireDate(), bankCard.getCardType(), createCardRequest.getUserId(),
                bank.getBankCode(), bankCard.getId());
        CardDto cardDto= cardDtoConverter.convert(cardRepository.save(card));
        System.out.println("Created Card" + cardDto.getCardNumber()
        + " " + cardDto.getCardHolderName()
        + " " + cardDto.getCardExpireDate()
        + " " + cardDto.getCardCvv()
        + " " + cardDto.getCardType()
        + " " + cardDto.getBinNumber()
        + " " + cardDto.getBankCode()
        + " " + cardDto.getUserId());
        return cardDto;
    }

    public CardDto findCardById(String id){
        return cardDtoConverter.convert(cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Card could not found by id: " + id)));
    }

    public CardJoinDto findCardByBinNumber(Integer binNumber){
        return cardRepository.findCardBankJoinByBinNumber(binNumber).orElseThrow(() -> new CardNotFoundException("Card could not found by id: " + binNumber));
    }

    public CardDto findCardByCardNumber(String cardNumber){
        return cardDtoConverter.convert(cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException("Card could not found by id: " + cardNumber)));
    }

    public CardJoinDtoList findCardsBankByUserId(String userId){
        return cardRepository.findCardBankJoinByUserId(userId).orElseThrow(() -> new CardNotFoundException("Card could not found by id: " + userId));
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

    public List<CardDto> findCardsByUserId(String userId){
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
