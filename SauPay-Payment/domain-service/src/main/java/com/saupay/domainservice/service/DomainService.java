package com.saupay.domainservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardJoinDto;
import com.saupay.domainservice.clients.card_client.CardJoinDtoList;
import com.saupay.domainservice.clients.card_client.CardServiceClient;
import com.saupay.domainservice.clients.request.CardRequest;
import com.saupay.domainservice.clients.request.PaymentCompleteRequest;
import com.saupay.domainservice.clients.transaction_client.*;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantDto;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantsDto;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionsDto;
import com.saupay.domainservice.clients.request.EncryptedPaymentRequest;
import com.saupay.domainservice.clients.request.PaymentRequest;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.clients.user_client.UserServiceClient;
import com.saupay.domainservice.exception.GeneralException;
import com.saupay.domainservice.response.TreeDSecureResponse;
import com.saupay.domainservice.utils.AndroidBackendCommuication;
import com.saupay.domainservice.utils.BackendBackendCommunication;
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
    private final ObjectMapper objectMapper;
    private final BackendBackendCommunication backendBackendCommunication;
    private final AndroidBackendCommuication androidBackendCommuication;


   // List<Transaction_MerchantDto> transactions = new ArrayList<>();
    public DomainService(CardServiceClient cardServiceClient, UserServiceClient userServiceClient, TransactionServiceClient transactionServiceClient, ObjectMapper objectMapper, BackendBackendCommunication backendBackendCommunication, AndroidBackendCommuication androidBackendCommuication) {
        this.cardServiceClient = cardServiceClient;
        this.userServiceClient = userServiceClient;
        this.transactionServiceClient = transactionServiceClient;
        this.objectMapper = objectMapper;
        this.backendBackendCommunication = backendBackendCommunication;
        this.androidBackendCommuication = androidBackendCommuication;
    }
    public UserDto getUser(String userId){
        return userServiceClient.getUser(userId).getBody();
    }
    public List<CardDto> getCardsUser(String userId){

        List<CardDto> cardDto= cardServiceClient.getCardsByUserId(userId).getBody();
        if(cardDto.isEmpty()){
            throw  new GeneralException("There is no card for this user","404");
        }
        return cardDto;
    }

    public CardJoinDto getCardByBinNumber(Integer binNumber){
        CardJoinDto cardJoinDto= cardServiceClient.getCardByBinNumber(binNumber).getBody();
        if(cardJoinDto==null){
            throw  new GeneralException("There is no card for this bin number","404");
        }
        return cardJoinDto;
    }

    public CardJoinDtoList getBankCardsByUserEmailForPayment(EncryptedPaymentRequest encryptedPaymentRequest,String signature, String randomKey){

        String decrypted =androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest,signature,randomKey);

        try {

            CardRequest cardRequest=objectMapper.readValue(decrypted,CardRequest.class);
            System.out.println("CardRequestEmail"+cardRequest.getEmail());

            UserDto userDto=userServiceClient.getUserByUserEmail(cardRequest.getEmail()).getBody();
            System.out.println("Find User ID"+userDto.getId());

            Transaction transaction=transactionServiceClient.getTransactionByToken(cardRequest.getPaymentToken()).getBody();
            transaction.setUserId(userDto.getId());
            System.out.println("Transaction ID"+transaction.getId() + "User ID"+transaction.getUserId()+ "Token"+transaction.getToken()
                    +"Amount"+transaction.getAmount()+"Status"+transaction.getStatus());
            Transaction updateTransaction= transactionServiceClient.updateTransaction(transaction).getBody();
            System.out.println("Update Transaction ID"+updateTransaction.getId() + "User ID"+updateTransaction.getUserId()+ "Token"+updateTransaction.getToken()
                    +"Amount"+updateTransaction.getAmount()+"Status"+updateTransaction.getStatus());

            CardJoinDtoList cardJoinDtoList= cardServiceClient.getCardsBankByUserId(userDto.getId()).getBody();
            System.out.println("CardJoinDtoList"+cardJoinDtoList);
            if(cardJoinDtoList==null){
                throw  new GeneralException("There is no card for this bin number","404");
            }
            return cardJoinDtoList;
        }catch (Exception e){
            throw  new GeneralException("There is no card for this bin number","404");
        }
    }

    public TreeDSecureResponse paymentCompleteRequestForTreeDSecure(EncryptedPaymentRequest encryptedPaymentRequest,String signature, String randomKey){

        String decrypted =androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest,signature,randomKey);

        try {
            PaymentCompleteRequest paymentCompleteRequest=objectMapper.readValue(decrypted, PaymentCompleteRequest.class);
            System.out.println("PaymentCompleteRequest"+paymentCompleteRequest.getPaymentToken()
                    +"CardNumber"+paymentCompleteRequest.getCardNumber());

            CardDto cardDto=cardServiceClient.getCardByCardNumber(paymentCompleteRequest.getCardNumber()).getBody();

            Transaction transaction=transactionServiceClient.getTransactionByToken(paymentCompleteRequest.getPaymentToken()).getBody();
            transaction.setCardId(cardDto.getId());
            System.out.println("Transaction ID"+transaction.getId() + "User ID"+transaction.getUserId()+ "Token"+transaction.getToken()
                    +"Amount"+transaction.getAmount()+"Status"+transaction.getStatus());
            Transaction updateTransaction= transactionServiceClient.updateTransaction(transaction).getBody();
            System.out.println("Update Transaction ID"+updateTransaction.getId() + "User ID"+updateTransaction.getUserId()+ "Token"+updateTransaction.getToken()
                    +"Amount"+updateTransaction.getAmount()+"Status"+updateTransaction.getStatus());

            TreeDSecureResponse treeDSecureResponse= transactionServiceClient.getTreeDSecureResponse(paymentCompleteRequest.getPaymentToken()).getBody();

            return treeDSecureResponse;


        }catch (Exception e){
            throw  new GeneralException("There is no card for this bin number","404");
        }
    }

/*    public CardJoinDtoList getCardsBankByUserId(String  userId){
        CardJoinDtoList cardJoinDtoList= cardServiceClient.getCardsBankByUserId(userId).getBody();
        if(cardJoinDtoList==null){
            throw  new GeneralException("There is no card for this bin number","404");
        }
        return cardJoinDtoList;
    }*/



    public TransactionsDto getTransactionByCardId(String cardId){

        TransactionsDto transactionsDto= transactionServiceClient.getTransactionByCardId(cardId).getBody();
        if(transactionsDto.getTransactions().isEmpty()){
            throw  new GeneralException("There is no transaction for this card","404");
        }
        return transactionsDto;
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

    public Transaction_MerchantsDto getTransactionMerchantByCardId(String cardId){

        Transaction_MerchantsDto transaction_merchantDto= transactionServiceClient.getTransactionsMerchantByCardId(cardId).getBody();
        if(transaction_merchantDto.getTransactions().isEmpty()){
            throw  new GeneralException("There is no transaction for this card","404");
        }
        return transaction_merchantDto;
    }


    public Transaction_MerchantDto getTransaction_MerchantByToken(String signature, String randomKey, EncryptedPaymentRequest encryptedPaymentRequest){
        String decryptedData = androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest, signature, randomKey);
        try {
            PaymentRequest paymentRequest = objectMapper.readValue(decryptedData, PaymentRequest.class);
            System.out.println("PaymentTokenObjectMapper: " + decryptedData);
            return transactionServiceClient.getTransactionMerchantByToken(paymentRequest.token).getBody();
        } catch (Exception e) {
            throw new GeneralException("There is no transaction for this user","404");
        }
    }



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
