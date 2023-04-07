package com.saupay.cardservice.service;


import com.saupay.cardservice.dto.BankDto;
import com.saupay.cardservice.dto.converter.BankDtoConverter;
import com.saupay.cardservice.model.Bank;
import com.saupay.cardservice.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BankService {

        private final BankRepository bankRepository;
        private final BankDtoConverter bankDtoConverter;

        public BankService(BankRepository bankRepository, BankDtoConverter bankDtoConverter) {
            this.bankRepository = bankRepository;
            this.bankDtoConverter = bankDtoConverter;
        }
        public BankDto createBank(String name,Integer code) {
            Bank bank = new Bank(name,code,new HashSet<>());
            return bankDtoConverter.convert(bankRepository.save(bank));
        }
        public String getBank(String id) {
            String bankId = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found")).getId();
            return bankId;
        }
}
