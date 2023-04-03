package com.saupay.domainservice.service;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardServiceClient;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.clients.user_client.UserServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainService {

    private final CardServiceClient cardServiceClient;
    private final UserServiceClient userServiceClient;

    public DomainService(CardServiceClient cardServiceClient, UserServiceClient userServiceClient) {
        this.cardServiceClient = cardServiceClient;
        this.userServiceClient = userServiceClient;
    }

    public List<CardDto> getCardsUser(String userId){
        // donen ResonseEntity'in body'sini alıyoruz
        List<CardDto> cardDtoList = new ArrayList<>(
                cardServiceClient.getCardsByUserId(userId).getBody().
                        stream()
                        .map(cardDto -> new CardDto(cardDto.getCardNumber(), cardDto.getBinNumber(), cardDto.getCardHolderName(), cardDto.getCardCvv(), cardDto.getCardExpireDate(), cardDto.getCardType(), cardDto.getUserId(), cardDto.getBank()))
                        .collect(Collectors.toList()));

        return cardDtoList;
    }

    public UserDto getUser(String userId){
        return userServiceClient.getUser(userId).getBody();
    }









}
