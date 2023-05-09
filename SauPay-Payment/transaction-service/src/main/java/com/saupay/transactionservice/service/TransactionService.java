package com.saupay.transactionservice.service;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.dto.converter.TransactionDtoConverter;
import com.saupay.transactionservice.dto.converter.TransactionsDtoConverter;
import com.saupay.transactionservice.exception.TransacitonNotFoundException;
import com.saupay.transactionservice.model.Transaction;
import com.saupay.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionDtoConverter transactionDtoConverter;

    private final TransactionsDtoConverter transactionsDtoConverter;

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter, TransactionsDtoConverter transactionsDtoConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoConverter = transactionDtoConverter;
        this.transactionsDtoConverter = transactionsDtoConverter;
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
            throw new TransacitonNotFoundException(transaction,"4000","Transaction not found");
        }

        return transactionsDtoConverter.convert(transaction.
                stream().
                map(transactionDtoConverter::convert).
                collect(Collectors.toList()));
    }

    public Transaction_MerchantsDto getTransactionMerchantByCardId(String cardId){
        return transactionRepository.findTransactionsMerchantById(cardId);
    }

    public String generatePaymentToken(String request){


        String token = "token_uretildi_saupay";

        return token;
    }

}
