package com.omer.Payment.service;

import com.omer.Payment.dto.CardDto;
import com.omer.Payment.dto.converter.CardDtoConverter;
import com.omer.Payment.model.Account;
import com.omer.Payment.model.BankCompany;
import com.omer.Payment.model.Card;
import com.omer.Payment.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CardService {


    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final BankCompanyService bankCompanyService;
    private final CardDtoConverter cardDtoConverter;


    public CardService(CardRepository cardRepository, AccountService accountService, BankCompanyService bankCompanyService, CardDtoConverter cardDtoConverter) {
        this.cardRepository = cardRepository;
        this.accountService = accountService;
        this.bankCompanyService = bankCompanyService;
        this.cardDtoConverter = cardDtoConverter;
    }

    public CardDto createCard(String id, String BankId) {
        Account account = accountService.findAccount(id);
        BankCompany bank = bankCompanyService.getBank(BankId);
        Card card = new Card("123456789","1234","1234","1234","1234","1234",new HashSet<>(),account,bank);
        return cardDtoConverter.convert(cardRepository.save(card));
    }

    public Card findCard(String id){
        return cardRepository.findById(id).orElse(null);
    }



}
