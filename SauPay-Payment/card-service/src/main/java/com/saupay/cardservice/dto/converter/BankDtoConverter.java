package com.saupay.cardservice.dto.converter;

import com.saupay.cardservice.dto.BankDto;
import com.saupay.cardservice.model.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankDtoConverter {

    public BankDto convert(Bank from)
    {
        return new BankDto(
                from.getId(),
                from.getBankName(),
                from.getBankCode()
        );
    }
}
