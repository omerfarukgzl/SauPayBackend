package com.saupay.transactionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.dto.converter.TransactionDtoConverter;
import com.saupay.transactionservice.dto.converter.TransactionsDtoConverter;
import com.saupay.transactionservice.exception.TransacitonNotFoundException;
import com.saupay.transactionservice.model.Transaction;
import com.saupay.transactionservice.repository.TransactionRepository;
import com.saupay.transactionservice.request.EncryptedPaymentRequest;
import com.saupay.transactionservice.utils.BackendBackendCommunication;
import com.saupay.transactionservice.utils.EncryptionUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionDtoConverter transactionDtoConverter;

    private final TransactionsDtoConverter transactionsDtoConverter;

    private final EncryptionUtil encryptionUtil;
    private final BackendBackendCommunication backendBackendCommunication;

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter, TransactionsDtoConverter transactionsDtoConverter, EncryptionUtil encryptionUtil,BackendBackendCommunication backendBackendCommunication) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoConverter = transactionDtoConverter;
        this.transactionsDtoConverter = transactionsDtoConverter;
        this.encryptionUtil = encryptionUtil;
        this.backendBackendCommunication = backendBackendCommunication;
    }
    public TransactionDto createTransaction(String cardId, String merchantId, String token){
        Transaction transaction = new Transaction(new BigDecimal(100.00), LocalDateTime.now(),cardId,merchantId,token);
        return transactionDtoConverter.convert(transactionRepository.save(transaction));
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

    public String generatePaymentToken(EncryptedPaymentRequest encryptedPaymentRequest, String signature, String randomKey){
        /****   Backend-Backend   ****/
        String decryptedData = backendBackendCommunication.GetBackendToBackendEncryptedAndSignatureDataTransaction(encryptedPaymentRequest, signature, randomKey);

        String token =  encryptionUtil.generateRandomKey();
        System.out.println("Token Response-Backend: " + token );

        String encryptedAndToken = backendBackendCommunication.SendBackendToBackendEncryptedDataTransaction(token);

        return encryptedAndToken;
    }



}
