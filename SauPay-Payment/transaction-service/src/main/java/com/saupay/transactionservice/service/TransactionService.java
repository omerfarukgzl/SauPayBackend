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
import com.saupay.transactionservice.response.PaymentResponse;
import com.saupay.transactionservice.utils.EncryptionUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionDtoConverter transactionDtoConverter;

    private final TransactionsDtoConverter transactionsDtoConverter;

    private final EncryptionUtil encryptionUtil;

    private final ObjectMapper objectMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter, TransactionsDtoConverter transactionsDtoConverter, EncryptionUtil encryptionUtil, ObjectMapper objectMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoConverter = transactionDtoConverter;
        this.transactionsDtoConverter = transactionsDtoConverter;
        this.encryptionUtil = encryptionUtil;
        this.objectMapper = objectMapper;
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

    public String generatePaymentToken(String encryptedPaymentRequest, String signature, String randomKey){

        try {
            Boolean isSignatureValid = encryptionUtil.checkSignature(signature,randomKey,encryptedPaymentRequest); // Verify signature
            if(!isSignatureValid){
                throw new RuntimeException("Signature is not valid");
            }
            String decryptedData = encryptionUtil.decrypt(encryptedPaymentRequest); // When signature is valid, decrypt data


            PaymentResponse paymentResponse = new PaymentResponse();
            System.out.println("paymentRequest: " + decryptedData );

            paymentResponse.setToken("Ben Ömer");


            String token = Jwts.builder()
                    .setSubject("userId") // kullanıcının kimliği
                    .setIssuedAt(new Date()) // token'ın oluşturulma tarihi
                    .setExpiration(new Date(System.currentTimeMillis() + 300)) // token'ın geçerlilik süresi (5 dk)
                    .signWith(SignatureAlgorithm.HS512,"54saupay54") // token'ın doğruluğunu sağlamak için kullanılan anahtar
                    .compact();

            return token;

        }catch (Exception e) {
            throw new RuntimeException("Signature is not valid");
        }



    }



}
