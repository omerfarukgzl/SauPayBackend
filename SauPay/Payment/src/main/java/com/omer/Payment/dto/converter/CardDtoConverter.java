package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.CardDto;
import com.omer.Payment.model.Card;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CardDtoConverter {

    private final TransactionDtoConverter transactionDtoConverter;
    private final BankDtoConverter bankDtoConverter;

    public CardDtoConverter(TransactionDtoConverter transactionDtoConverter, BankDtoConverter bankDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
        this.bankDtoConverter = bankDtoConverter;
    }

    public CardDto convert(Card from)
    {
        return new CardDto(
                from.getId(),
                from.getCardNumber(),
                from.getBinNumber(),
                from.getCardHolderName(),
                from.getCardCvv(),
                from.getCardExpireDate(),
                from.getCardType(),
                bankDtoConverter.convert(from.getBankCompany()),
                from.getTransactions()
                        .stream()
                        .map(transactionDtoConverter::convert)
                        .collect(Collectors.toSet())
        );
    }


}
