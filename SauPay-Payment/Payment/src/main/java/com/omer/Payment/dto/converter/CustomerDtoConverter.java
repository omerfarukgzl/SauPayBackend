package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.CustomerAccountDto;
import com.omer.Payment.dto.CustomerDto;
import com.omer.Payment.model.Account;
import com.omer.Payment.model.Customer;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    private final AccountDtoConverter accountDtoConverter;

    public CustomerDtoConverter(AccountDtoConverter accountDtoConverter) {
        this.accountDtoConverter = accountDtoConverter;
    }

    public CustomerDto convert(Customer from) {
        return new CustomerDto(from.getId(),
                from.getCustomerName(),
                from.getCustomerSurname(),
                from.getCustomerEmail(),
                from.getCustomerPhone(),
                from.getCustomerTC(),
                accountDtoConverter.convert(from.getAccount()));
    }


}
