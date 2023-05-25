package com.saupay.cardservice.dto.converter;

import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.model.Card;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class CardDtoConverter {

    private final BankDtoConverter bankDtoConverter;

    public CardDtoConverter(BankDtoConverter bankDtoConverter) {
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
                from.getUserId(),
                from.getBankCode());
    }
}
