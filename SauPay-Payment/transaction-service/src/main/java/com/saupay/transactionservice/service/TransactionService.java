package com.saupay.transactionservice.service;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.dto.converter.TransactionDtoConverter;
import com.saupay.transactionservice.dto.converter.TransactionsDtoConverter;
import com.saupay.transactionservice.model.Transaction;
import com.saupay.transactionservice.repository.TransactionRepository;
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

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter, TransactionsDtoConverter transactionsDtoConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoConverter = transactionDtoConverter;
        this.transactionsDtoConverter = transactionsDtoConverter;
    }
    public TransactionDto createTransaction(String cardId, String merchantId){
        Transaction transaction = new Transaction(new BigDecimal(100.00), LocalDateTime.now(),cardId,merchantId);
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

        return transactionsDtoConverter.convert(transactionRepository.findByCardId(cardId).
                stream().
                map(transactionDtoConverter::convert).
                collect(Collectors.toList()));





        /*return transactionRepository.findByCardId(cardId).
                stream().
                map(transactionDtoConverter::convert).
                collect(Collectors.toList());*/
    }

    public List<Transaction_MerchantDto> getTransactionMerchantByCardId(String cardId){
        return transactionRepository.findTransactionsMerchantById(cardId);
    }


}
