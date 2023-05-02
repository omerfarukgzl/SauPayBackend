package com.saupay.transactionservice.dto.converter;

import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.dto.TransactionsDto;
import com.saupay.transactionservice.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionsDtoConverter {
    public TransactionsDto convert(List<TransactionDto> from) {

        TransactionsDto transactionsDto = new TransactionsDto();
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for (TransactionDto transactionDto : from) {
            transactionDtoList.add(transactionDto);
        }
        transactionsDto.setTransactions(transactionDtoList);
        return transactionsDto;
    }
}
