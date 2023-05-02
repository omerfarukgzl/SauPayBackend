package com.saupay.domainservice.service;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardServiceClient;
import com.saupay.domainservice.clients.transaction_client.TransactionDto;
import com.saupay.domainservice.clients.transaction_client.TransactionServiceClient;
import com.saupay.domainservice.clients.transaction_client.Transaction_MerchantDto;
import com.saupay.domainservice.clients.transaction_client.TransactionsDto;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.clients.user_client.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DomainService {

    private final CardServiceClient cardServiceClient;
    private final UserServiceClient userServiceClient;

    private final TransactionServiceClient transactionServiceClient;
    public DomainService(CardServiceClient cardServiceClient, UserServiceClient userServiceClient, TransactionServiceClient transactionServiceClient) {
        this.cardServiceClient = cardServiceClient;
        this.userServiceClient = userServiceClient;
        this.transactionServiceClient = transactionServiceClient;
    }
    public UserDto getUser(String userId){
        return userServiceClient.getUser(userId).getBody();
    }
    public List<CardDto> getCardsUser(String userId){
        return cardServiceClient.getCardsByUserId(userId).getBody();
    }

    public TransactionsDto getTransactionByCardId(String cardId){
        return transactionServiceClient.getTransactionByCardId(cardId).getBody();
    }
    public TransactionsDto getTransactionByUserId(String userId){
        List<CardDto> cardDtoList = getCardsUser(userId);
        TransactionsDto transactions = new TransactionsDto();
        transactions.setTransactions(new ArrayList<>());
        for (CardDto cardDto : cardDtoList) {
            transactions.getTransactions().addAll(getTransactionByCardId(cardDto.getId()).getTransactions());
        }

        return transactions;
    }
    public List<Transaction_MerchantDto> getTransaction_MerchantByUserId(String cardId){
        return transactionServiceClient.getTransactionMerchantByCardId(cardId).getBody();

    }

}
