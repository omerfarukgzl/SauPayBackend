package com.saupay.domainservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saupay.domainservice.clients.card_client.CardDto;
import com.saupay.domainservice.clients.card_client.CardJoinDto;
import com.saupay.domainservice.clients.card_client.CardJoinDtoList;
import com.saupay.domainservice.clients.card_client.CardServiceClient;
import com.saupay.domainservice.clients.card_client.request.CreateCardRequest;
import com.saupay.domainservice.clients.request.*;
import com.saupay.domainservice.clients.transaction_client.*;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantDto;
import com.saupay.domainservice.clients.transaction_client.dto.Transaction_MerchantsDto;
import com.saupay.domainservice.clients.transaction_client.dto.TransactionsDto;
import com.saupay.domainservice.clients.user_client.UserDto;
import com.saupay.domainservice.clients.user_client.UserServiceClient;
import com.saupay.domainservice.exception.GeneralException;
import com.saupay.domainservice.response.AddCard;
import com.saupay.domainservice.response.PaymentBankResponse;
import com.saupay.domainservice.response.TreeDSecureResponse;
import com.saupay.domainservice.utils.AndroidBackendCommuication;
import com.saupay.domainservice.utils.BackendBackendCommunication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DomainService {

    private final CardServiceClient cardServiceClient;
    private final UserServiceClient userServiceClient;

    private final TransactionServiceClient transactionServiceClient;
    private final ObjectMapper objectMapper;
    private final BackendBackendCommunication backendBackendCommunication;
    private final AndroidBackendCommuication androidBackendCommuication;

    private final KafkaTemplate<String, String> kafkaTemplate;


   // List<Transaction_MerchantDto> transactions = new ArrayList<>();
    public DomainService(CardServiceClient cardServiceClient, UserServiceClient userServiceClient, TransactionServiceClient transactionServiceClient, ObjectMapper objectMapper, BackendBackendCommunication backendBackendCommunication, AndroidBackendCommuication androidBackendCommuication, KafkaTemplate<String, String> kafkaTemplate) {
        this.cardServiceClient = cardServiceClient;
        this.userServiceClient = userServiceClient;
        this.transactionServiceClient = transactionServiceClient;
        this.objectMapper = objectMapper;
        this.backendBackendCommunication = backendBackendCommunication;
        this.androidBackendCommuication = androidBackendCommuication;
        this.kafkaTemplate = kafkaTemplate;
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

    public CardJoinDtoList getBankCardsByUserEmail(EncryptedPaymentRequest encryptedPaymentRequest,String signature, String randomKey){

        String decrypted =androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest,signature,randomKey);

        try {

            CardListRequest cardListRequest=objectMapper.readValue(decrypted,CardListRequest.class);
            System.out.println("CardRequestEmail"+cardListRequest.getEmail());

            UserDto userDto=userServiceClient.getUserByUserEmail(cardListRequest.getEmail()).getBody();
            System.out.println("Find User ID"+userDto.getId());

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


    public AddCard addCard(EncryptedPaymentRequest encryptedPaymentRequest, String signature, String randomKey){

            String decrypted =androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest,signature,randomKey);

            try {
                AddCardRequest addCardRequest = objectMapper.readValue(decrypted, AddCardRequest.class);
                System.out.println("CardRequest" + addCardRequest.getCardNumber() + "CardHolderName" + addCardRequest.getCardHolderName()
                        + "CardExpireDate" + addCardRequest.getCardExpireDate() + "CardCvv" + addCardRequest.getCardCvv()
                        + "Email" + addCardRequest.getEmail());

                UserDto userDto = userServiceClient.getUserByUserEmail(addCardRequest.getEmail()).getBody();
                System.out.println("Find User ID" + userDto.getId()
                        + "Email" + userDto.getCustomerEmail()
                        + "Name" + userDto.getCustomerName()
                        + "Surname" + userDto.getCustomerSurname()
                        + "Phone" + userDto.getCustomerPhone()
                        + "Tc" + userDto.getCustomerTC());

                CreateCardRequest createCardRequest = new CreateCardRequest();
                createCardRequest.setCardNumber(addCardRequest.getCardNumber());
                createCardRequest.setBinNumber(addCardRequest.getBinNumber().toString());
                createCardRequest.setCardHolderName(addCardRequest.getCardHolderName());
                createCardRequest.setCardExpireDate(addCardRequest.getCardExpireDate());
                createCardRequest.setCardCvv(addCardRequest.getCardCvv());
                createCardRequest.setUserId(userDto.getId());
                CardDto cardDto = cardServiceClient.createCard(createCardRequest).getBody();
                if (cardDto == null) {
                    throw new GeneralException("Kart Eklenemedi", "404");
                }
                System.out.println("basarili card ekleme");

                return new AddCard(true);
            }
            catch (Exception e){
                throw  new GeneralException("Geçersiz Kard Bilgileri","404");
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
    public Transaction_MerchantsDto getTransaction_MerchantByUserEmail(String email){
        System.out.println("Email"+email);
        UserDto userDto=userServiceClient.getUserByUserEmail(email).getBody();
        System.out.println("User ID"+userDto.getId());
        Transaction_MerchantsDto transactions = new Transaction_MerchantsDto();
        transactions.setTransactions(new ArrayList<>());

        List<CardDto> cardDtoList = getCardsUser(userDto.getId());
        if (cardDtoList.isEmpty()){
            throw new GeneralException("There is no card for this user","400");
        }
        System.out.println("CardDtoList"+cardDtoList.get(0).getId());

        Comparator<Transaction_MerchantDto> dateComparator = Comparator.comparing(Transaction_MerchantDto::getLocalDateTime);
        List <Transaction_MerchantDto> transaction_merchantDtoList = new ArrayList<>();
        for (CardDto cardDto : cardDtoList) {
            transaction_merchantDtoList .addAll(getTransactionMerchantByCardId(cardDto.getId()).getTransactions());
        }
        transaction_merchantDtoList.sort(dateComparator.reversed());
        transactions.getTransactions().addAll(transaction_merchantDtoList);
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

    public PaymentBankResponse paymentBank(EncryptedPaymentRequest encryptedPaymentRequest, String signature, String randomKey){
        System.out.println("PaymentBankService");
        String decryptedData = androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest, signature, randomKey);
        try {
            BankRequest bankRequest = objectMapper.readValue(decryptedData, BankRequest.class);
            System.out.println("PaymentTokenObjectMapper: " + decryptedData);
            PaymentBankResponse response= transactionServiceClient.paymentBank(decryptedData).getBody();

            Transaction transaction=transactionServiceClient.getTransactionByToken(bankRequest.getToken()).getBody();
            transaction.setStatus(true);
            Transaction updateTransaction= transactionServiceClient.updateTransaction(transaction).getBody();
            System.out.println("Update Transaction ID"+updateTransaction.getId() + "User ID"+updateTransaction.getUserId()+ "Token"+updateTransaction.getToken()
                    +"Amount"+updateTransaction.getAmount()+"Status"+updateTransaction.getStatus());

            String notificationMessage = "Dear customer \n Your account create transaction has been succeed. Your amount info is %s";
            String senderMessage = String.format(notificationMessage,transaction.getAmount());
            kafkaTemplate.send("transfer-notification",  senderMessage);

            return response;
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
