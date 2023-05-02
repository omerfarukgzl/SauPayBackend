package com.saupay.transactionservice.dto.converter;


import com.saupay.transactionservice.dto.TransactionDto;
import com.saupay.transactionservice.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convert(Transaction from) {
        return new TransactionDto(
                from.getId(),
                from.getAmount(),
                from.getLocalDateTime()

        );
    }
}
