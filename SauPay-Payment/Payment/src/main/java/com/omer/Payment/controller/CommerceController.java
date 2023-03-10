package com.omer.Payment.controller;

import com.omer.Payment.dto.Commerce_CompanyDto;
import com.omer.Payment.service.CommerceCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommerceController {

    private final CommerceCompanyService commerceCompanyService;

    public CommerceController(CommerceCompanyService commerceCompanyService) {
        this.commerceCompanyService = commerceCompanyService;
    }

    @PostMapping("/createCommerce")
    public ResponseEntity<Commerce_CompanyDto> createCommerce(){
        Commerce_CompanyDto commerce_companyDto = commerceCompanyService.createCommerceCompany();
        return ResponseEntity.ok(commerce_companyDto);
    }
}
