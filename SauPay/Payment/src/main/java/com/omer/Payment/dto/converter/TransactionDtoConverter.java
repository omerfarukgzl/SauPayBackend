package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.TransactionDto;
import com.omer.Payment.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    private final CommerceCompanyDtoConverter commerceCompanyDtoConverter;

    public TransactionDtoConverter(CommerceCompanyDtoConverter commerceCompanyDtoConverter) {
        this.commerceCompanyDtoConverter = commerceCompanyDtoConverter;
    }

    public TransactionDto convert(Transaction from) {
        return new TransactionDto(
                from.getId(),
                from.getAmaount(),
                from.getLocalDateTime(),
                commerceCompanyDtoConverter.convert(from.getCommerceCompany())
        );
    }
}
