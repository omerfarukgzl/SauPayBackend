package com.omer.Payment.service;

import com.omer.Payment.model.Card;
import com.omer.Payment.model.CommerceCompany;
import com.omer.Payment.model.Transaction;
import com.omer.Payment.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardService cardService;

    private final CommerceCompanyService commerceCompanyService;

    public TransactionService(TransactionRepository transactionRepository, CardService cardService, CommerceCompanyService commerceCompanyService) {
        this.transactionRepository = transactionRepository;
        this.commerceCompanyService = commerceCompanyService;
        this.cardService = cardService;
    }

    public void createTransaction(String id, String CommerceId){

        Card card = cardService.findCard(id);
        CommerceCompany commerceCompany = commerceCompanyService.getCommerceCompany(CommerceId);
        Transaction transaction = new Transaction(card,new BigDecimal(100.00), LocalDateTime.now(),commerceCompany);
        transactionRepository.save(transaction);
    }


}
