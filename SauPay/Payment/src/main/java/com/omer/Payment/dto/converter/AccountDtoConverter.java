package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.AccountDto;
import com.omer.Payment.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final CardDtoConverter cardDtoConverter;

    public AccountDtoConverter(CardDtoConverter cardDtoConverter) {
        this.cardDtoConverter = cardDtoConverter;
    }

    public AccountDto convert(Account from) {
        return new AccountDto(
                from.getId(),
                from.getCreationDate(),
                from.getCards().stream()
                .map(cardDtoConverter::convert)
                .collect(Collectors.toSet())
        );
    }






}
