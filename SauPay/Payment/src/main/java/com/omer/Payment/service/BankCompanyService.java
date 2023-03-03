package com.omer.Payment.service;

import com.omer.Payment.dto.BankCompanyDto;
import com.omer.Payment.dto.converter.BankDtoConverter;
import com.omer.Payment.model.BankCompany;
import com.omer.Payment.repository.BankCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class BankCompanyService {

        private final BankCompanyRepository bankCompanyRepository;
        private final BankDtoConverter bankDtoConverter;

        public BankCompanyService(BankCompanyRepository bankCompanyRepository, BankDtoConverter bankDtoConverter) {
            this.bankCompanyRepository = bankCompanyRepository;
            this.bankDtoConverter = bankDtoConverter;
        }

        public BankCompanyDto createBank() {
            BankCompany bankCompany = new BankCompany("SauBank","545454",new HashSet<>());
            return bankDtoConverter.convert(bankCompanyRepository.save(bankCompany));
        }

        public BankCompany getBank(String id) {
            BankCompany bankCompany = bankCompanyRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found"));
            return bankCompany;
        }
}
