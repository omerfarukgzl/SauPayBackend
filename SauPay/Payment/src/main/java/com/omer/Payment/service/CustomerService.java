package com.omer.Payment.service;

import com.omer.Payment.dto.AccountTransactionDto;
import com.omer.Payment.dto.CustomerAccountDto;
import com.omer.Payment.dto.CustomerDto;
import com.omer.Payment.dto.converter.CustomerAccountDtoConverter;
import com.omer.Payment.dto.converter.CustomerDtoConverter;
import com.omer.Payment.model.Account;
import com.omer.Payment.model.Customer;
import com.omer.Payment.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, AccountService accountService, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.customerDtoConverter = customerDtoConverter;
    }


    public CustomerDto createCustomer() {

        Account account = accountService.createAccount();
        Customer customer = new Customer("", "Omer", "Faruk", "guzelomerfaruk@gmail.com", "5555", "132344343443", "1234", account);
        CustomerDto customerDto = customerDtoConverter.convert(customerRepository.save(customer));

        return customerDto;
    }

    public CustomerDto findCustomer(String id) {
        return customerDtoConverter.convert(customerRepository.findById(id).orElse(null));
    }




}
