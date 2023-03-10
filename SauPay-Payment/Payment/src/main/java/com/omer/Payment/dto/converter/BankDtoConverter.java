package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.BankCompanyDto;
import com.omer.Payment.model.BankCompany;
import org.springframework.stereotype.Component;

@Component
public class BankDtoConverter {

    public BankCompanyDto convert(BankCompany from)
    {
        return new BankCompanyDto(
                from.getId(),
                from.getBankName(),
                from.getBankCode()
        );
    }
}
