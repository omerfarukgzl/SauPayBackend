package com.omer.Payment.service;

import com.omer.Payment.dto.Commerce_CompanyDto;
import com.omer.Payment.dto.converter.CommerceCompanyDtoConverter;
import com.omer.Payment.model.CommerceCompany;
import com.omer.Payment.repository.CommerceCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CommerceCompanyService {

    private final CommerceCompanyRepository commerceCompanyRepository;

    private final CommerceCompanyDtoConverter commerce_companyDtoConverter;

    public CommerceCompanyService(CommerceCompanyRepository commerceCompanyRepository, CommerceCompanyDtoConverter commerce_companyDtoConverter) {
        this.commerceCompanyRepository = commerceCompanyRepository;
        this.commerce_companyDtoConverter = commerce_companyDtoConverter;
    }

    public Commerce_CompanyDto createCommerceCompany() {
        CommerceCompany commerceCompany = new CommerceCompany("SauPay","543454",new HashSet<>());
       return commerce_companyDtoConverter.convert(commerceCompanyRepository.save(commerceCompany));
    }
    public CommerceCompany getCommerceCompany(String id) {
        CommerceCompany commerceCompany = commerceCompanyRepository.findById(id).orElseThrow(() -> new RuntimeException("Commerce Company not found"));
        return commerceCompany;
    }
}
