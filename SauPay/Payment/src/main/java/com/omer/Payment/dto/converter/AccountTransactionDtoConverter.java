package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.AccountTransactionDto;
import com.omer.Payment.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountTransactionDtoConverter {

    private final CardDtoConverter cardDtoConverter;

    public AccountTransactionDtoConverter(CardDtoConverter cardDtoConverter) {
        this.cardDtoConverter = cardDtoConverter;
    }

    public AccountTransactionDto convert(Account from) {
        return new AccountTransactionDto(
                from.getId(),
                from.getCreationDate(),
                from.getCards()
                        .stream()
                        .map(cardDtoConverter::convert)
                        .collect(Collectors.toSet())
                );
    }
}
