package com.omer.Payment.controller;

import com.omer.Payment.dto.BankCompanyDto;
import com.omer.Payment.service.BankCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankCompanyController {

    private final BankCompanyService bankCompanyService;


    public BankCompanyController( BankCompanyService bankCompanyService) {
        this.bankCompanyService = bankCompanyService;
    }

    @PostMapping("/createBank")
    public ResponseEntity<BankCompanyDto> createCustomer(){
        BankCompanyDto bankDto = bankCompanyService.createBank();
        return ResponseEntity.ok(bankDto);
    }

}
