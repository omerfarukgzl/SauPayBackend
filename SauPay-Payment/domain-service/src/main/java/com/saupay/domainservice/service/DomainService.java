package com.saupay.domainservice.service;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardServiceClient;
import com.saupay.domainservice.clients.transaction_client.TransactionDto;
import com.saupay.domainservice.clients.transaction_client.TransactionServiceClient;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.clients.user_client.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<TransactionDto> getTransactionByUserId(String userId){
        List<CardDto> cardDtoList = getCardsUser(userId);
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for (CardDto cardDto : cardDtoList) {
            transactionDtoList.addAll(transactionServiceClient.getTransactionByCardId(cardDto.getId()).getBody());
        }
        // stream ile yapılabilir
        //cardDtoList.stream().map(cardDto -> transactionServiceClient.getTransactionByCardId(cardDto.getId()).getBody()).collect(Collectors.toList());
        return transactionDtoList;
    }
    public List<TransactionDto> getTransactionByCardId(String cardId){
        return transactionServiceClient.getTransactionByCardId(cardId).getBody();
    }

}
