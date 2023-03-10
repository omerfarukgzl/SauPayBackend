package com.saupay.cardservice.controller;

import com.saupay.cardservice.dto.BankDto;
import com.saupay.cardservice.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/bank")
public class BankController {

    private final BankService bankService;


    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/createBank")
    public ResponseEntity<BankDto> createCustomer(){
        BankDto bankDto = bankService.createBank("Bank of America",54);
        return ResponseEntity.ok(bankDto);
    }



}
