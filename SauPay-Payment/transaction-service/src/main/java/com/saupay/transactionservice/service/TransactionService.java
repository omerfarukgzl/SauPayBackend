package com.saupay.transactionservice.service;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.converter.TransactionDtoConverter;
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

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionDtoConverter = transactionDtoConverter;
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

    public List<TransactionDto> getTransactionByCardId(String cardId){
        return transactionRepository.findByCardId(cardId).
                stream().
                map(transactionDtoConverter::convert).
                collect(Collectors.toList());
    }


}
