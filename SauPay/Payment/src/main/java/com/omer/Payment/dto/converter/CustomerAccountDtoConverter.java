package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.AccountTransactionDto;
import com.omer.Payment.dto.CustomerAccountDto;
import com.omer.Payment.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerAccountDtoConverter {


    private final AccountTransactionDtoConverter accountTransactionDtoConverter;

    public CustomerAccountDtoConverter(AccountTransactionDtoConverter accountTransactionDtoConverter) {
        this.accountTransactionDtoConverter = accountTransactionDtoConverter;
    }

    public CustomerAccountDto convert(Customer from) {
        return new CustomerAccountDto(from.getId(),
                from.getCustomerName(),
                from.getCustomerSurname(),
                from.getCustomerEmail(),
                from.getCustomerPhone(),
                from.getCustomerTC(),
                accountTransactionDtoConverter.convert(from.getAccount()));
    }
}
