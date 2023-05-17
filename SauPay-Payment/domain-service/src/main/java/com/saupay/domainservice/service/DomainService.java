package com.saupay.domainservice.service;

import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardServiceClient;
import com.saupay.domainservice.clients.transaction_client.*;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantsDto;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionsDto;
import com.saupay.domainservice.clients.transaction_client.request.EncryptedPaymentRequest;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.clients.user_client.UserServiceClient;
import com.saupay.domainservice.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DomainService {

    private final CardServiceClient cardServiceClient;
    private final UserServiceClient userServiceClient;

    private final TransactionServiceClient transactionServiceClient;

   // List<Transaction_MerchantDto> transactions = new ArrayList<>();
    public DomainService(CardServiceClient cardServiceClient, UserServiceClient userServiceClient, TransactionServiceClient transactionServiceClient) {
        this.cardServiceClient = cardServiceClient;
        this.userServiceClient = userServiceClient;
        this.transactionServiceClient = transactionServiceClient;
    }
    public UserDto getUser(String userId){
        return userServiceClient.getUser(userId).getBody();
    }
    public List<CardDto> getCardsUser(String userId){

        List<CardDto> cardDto= cardServiceClient.getCardsByUserId(userId).getBody();
       /* if(cardDto.isEmpty()){
            throw  new GeneralException(cardDto,"401","There is no card for this user");
        }*/
        return cardDto;
    }

    public TransactionsDto getTransactionByCardId(String cardId){

        TransactionsDto transactionsDto= transactionServiceClient.getTransactionByCardId(cardId).getBody();
        if(transactionsDto.getTransactions().isEmpty()){
            throw  new GeneralException("There is no transaction for this card","404");
        }
        return transactionsDto;
    }

    public Transaction_MerchantsDto getTransactionMerchantByCardId(String cardId){

        Transaction_MerchantsDto transaction_merchantDto= transactionServiceClient.getTransactionMerchantByCardId(cardId).getBody();
        if(transaction_merchantDto.getTransactions().isEmpty()){
            throw  new GeneralException("There is no transaction for this card","404");
        }
        return transaction_merchantDto;
    }

    public TransactionsDto getTransactionByUserId(String userId){

        TransactionsDto transactions = new TransactionsDto();
        transactions.setTransactions(new ArrayList<>());

        List<CardDto> cardDtoList = getCardsUser(userId);
        if (cardDtoList.isEmpty()){
            throw  new GeneralException("There is no card for this user","404");
        }


        for (CardDto cardDto : cardDtoList) {
            transactions.getTransactions().addAll(getTransactionByCardId(cardDto.getId()).getTransactions());
        }
        if(transactions.getTransactions().isEmpty()){
            throw  new GeneralException("There is no transaction for this user","404");
        }

        return transactions;
    }
    public Transaction_MerchantsDto getTransaction_MerchantByUserId(String userId){

        Transaction_MerchantsDto transactions = new Transaction_MerchantsDto();
        transactions.setTransactions(new ArrayList<>());

        List<CardDto> cardDtoList = getCardsUser(userId);
        if (cardDtoList.isEmpty()){
            throw new GeneralException("There is no card for this user","400");
        }

        for (CardDto cardDto : cardDtoList) {
            transactions.getTransactions().addAll(getTransactionMerchantByCardId(cardDto.getId()).getTransactions());
        }
        if(transactions.getTransactions().isEmpty()){
            throw  new GeneralException("There is no transaction for this user","404");
        }

        return transactions;

    }

/*    public String generatePaymentToken(HttpServletRequest httpServletRequest, EncryptedPaymentRequest encryptedPaymentRequest){
        return transactionServiceClient.generatePaymentToken(httpServletRequest,encryptedPaymentRequest).getBody();
    }*/

}





/*  public List<TransactionDto> getTransactionByUserId(String userId){
        List<CardDto> cardDtoList = getCardsUser(userId);
        if (cardDtoList.isEmpty()){
            throw  new GeneralException(cardDtoList,"404","There is no card for this user");
        }

        List <TransactionDto> transactions = new ArrayList<>();
        //transactions.setTransactions(new ArrayList<>());
        for (CardDto cardDto : cardDtoList) {
            transactions.addAll(getTransactionByCardId(cardDto.getId()).getTransactions());
        }
        if(transactions.isEmpty()){
            throw  new GeneralException(transactions,"404","There is no transaction for this user");
        }

        return transactions;
    }
    }*/
