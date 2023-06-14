package com.saupay.transactionservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.dto.converter.TransactionDtoConverter;
import com.saupay.transactionservice.dto.converter.TransactionsDtoConverter;
import com.saupay.transactionservice.exception.TransacitonNotFoundException;
import com.saupay.transactionservice.model.Merchant;
import com.saupay.transactionservice.model.Transaction;
import com.saupay.transactionservice.repository.TransactionRepository;
import com.saupay.transactionservice.request.BankRequest;
import com.saupay.transactionservice.request.EncryptedPaymentRequest;
import com.saupay.transactionservice.request.PaymentRequest;
import com.saupay.transactionservice.response.PaymentBankResponse;
import com.saupay.transactionservice.response.TreeDSecureResponse;
import com.saupay.transactionservice.utils.AndroidBackendCommuication;
import com.saupay.transactionservice.utils.BackendBackendCommunication;
import com.saupay.transactionservice.utils.EncryptionUtil;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.saupay.transactionservice.utils.Constants.SECRET_KEY_BACKEND_BANK;
import static com.saupay.transactionservice.utils.Constants.SECRET_KEY_BACKEND_MERCHANT;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionDtoConverter transactionDtoConverter;

    private final TransactionsDtoConverter transactionsDtoConverter;

    private final EncryptionUtil encryptionUtil;
    private final ObjectMapper objectMapper;
    private final BackendBackendCommunication backendBackendCommunication;
    private final AndroidBackendCommuication androidBackendCommuication;
    private final MerchantService merchantService;

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter, TransactionsDtoConverter transactionsDtoConverter, EncryptionUtil encryptionUtil, ObjectMapper objectMapper, BackendBackendCommunication backendBackendCommunication, AndroidBackendCommuication androidBackendCommuication, MerchantService merchantService) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoConverter = transactionDtoConverter;
        this.transactionsDtoConverter = transactionsDtoConverter;
        this.encryptionUtil = encryptionUtil;
        this.objectMapper = objectMapper;
        this.backendBackendCommunication = backendBackendCommunication;
        this.androidBackendCommuication = androidBackendCommuication;
        this.merchantService = merchantService;
    }
    public TransactionDto createTransaction(String cardId,String userId,BigDecimal amount, String merchantId, String token,Boolean status){
       /* Transaction transaction = new Transaction(new BigDecimal(100.00), LocalDateTime.now(),cardId,merchantId,token);*/
        Transaction transaction = new Transaction(amount,LocalDateTime.now(),cardId,merchantId,userId,token,status);
        return transactionDtoConverter.convert(transactionRepository.save(transaction));
    }
    // update transaction
    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    public TransactionDto getTransaction(String id){
        return transactionDtoConverter.convert(transactionRepository.findById(id).get());
    }

    public List<TransactionDto> getAllTransaction(){
        return transactionRepository.findAll().
                stream().
                map(transactionDtoConverter::convert).
                collect(Collectors.toList());
    }

    public TransactionsDto getTransactionByCardId(String cardId){

        List<Transaction> transaction = transactionRepository.findByCardId(cardId);
        if (transaction.isEmpty()){
            throw new TransacitonNotFoundException("Transaction not found","4000");
        }
        return transactionsDtoConverter.convert(transaction.
                stream().
                map(transactionDtoConverter::convert).
                collect(Collectors.toList()));
    }

    public Transaction_MerchantsDto getTransactionMerchantByCardId(String cardId){
        return transactionRepository.findTransactionsMerchantById(cardId);
    }
    public Transaction_MerchantDto getTransactionMerchantByToken(String token/*EncryptedPaymentRequest encryptedPaymentRequest, String signature, String randomKey*/){

/*        String decryptedData = androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest, signature, randomKey);
        System.out.println("Decrypted-Token Request-Mobile: " + decryptedData);*/

        Transaction_MerchantDto transaction_merchantDto = transactionRepository.findTransactionsMerchantByToken(token);

        return transaction_merchantDto;
    }

    public Transaction getTransactionByToken(String token){
        return transactionRepository.findByToken(token).orElseThrow(() -> new TransacitonNotFoundException("Transaction not found","4000"));
    }


    public String generatePaymentToken(EncryptedPaymentRequest encryptedPaymentRequest, String signature, String randomKey){
        /****   Backend-Backend   ****/
        String decryptedData = backendBackendCommunication.getBackendToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest, signature, randomKey,SECRET_KEY_BACKEND_MERCHANT);

        String token =  encryptionUtil.generateRandomKey();
        System.out.println("Token Response-Backend: " + token );
        try {
            PaymentRequest paymentRequest = objectMapper.readValue(decryptedData, PaymentRequest.class);
            Merchant merchant = merchantService.getMerchant(paymentRequest.getMerchantCode());
            createTransaction(null,null,paymentRequest.getAmount(),merchant.getId(),token,false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String encryptedAndToken = backendBackendCommunication.sendBackendToBackendEncryptedDataTransaction(token,SECRET_KEY_BACKEND_MERCHANT);

        return encryptedAndToken;
    }

    public TreeDSecureResponse getTreeDSecureResponse(String token){
        return transactionRepository.findTreeDSecureResponseByToken(token);
    }

    public PaymentBankResponse paymentBank(/*EncryptedPaymentRequest encryptedPaymentRequest, String signature, String randomKey*/ String decryptedData){

     /*   *//****   Android-Backend   ****//*

        String decryptedData = androidBackendCommuication.AndroidToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest, signature, randomKey);

        *//****   Backend-Backend   ****//*
        String rndmKey = encryptionUtil.generateRandomKey();
        String encryptedAndSignatureTokenResponse = backendBackendCommunication.SendBackendToBackendEncryptedAndSignatureDataPayment(decryptedData,rndmKey,SECRET_KEY_BACKEND_BANK);
        String decryptedTokenResponse = backendBackendCommunication.GetBackendToBackendSignatureDataPayment(encryptedAndSignatureTokenResponse,SECRET_KEY_BACKEND_BANK);
*/

        System.out.println("PaymentBankMService-Backend: " + decryptedData);

       // String decypted = backendBackendCommunication.GetBackendToBackendSignatureDataPayment(decryptedData.getData(),SECRET_KEY_BACKEND_MERCHANT);

        String rndmKey = encryptionUtil.generateRandomKey();
        String response = backendBackendCommunication.SendBackendToBackendEncryptedAndSignatureDataPayment(decryptedData,rndmKey,SECRET_KEY_BACKEND_BANK);
        //String decyptedResponse = backendBackendCommunication.GetBackendToBackendSignatureDataPayment(encryptedAndSignatureTokenResponse,SECRET_KEY_BACKEND_BANK);
        System.out.println("BankResponse-Backend: " + response);
        PaymentBankResponse paymentBankResponse = new PaymentBankResponse();
        try {
            paymentBankResponse = objectMapper.readValue(response,PaymentBankResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
/*        paymentBankResponse.setSuccess(true);*/
        return paymentBankResponse;
    }





    /*        Transaction transaction = getTransactionByToken(token);
        transaction.setStatus(true);
        updateTransaction(transaction);*/





}
