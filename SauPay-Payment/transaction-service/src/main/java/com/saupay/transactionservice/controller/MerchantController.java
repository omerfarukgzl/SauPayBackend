package com.saupay.transactionservice.controller;

import com.saupay.transactionservice.dto.MerchantDto;
import com.saupay.transactionservice.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController( MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/createMerchant")
    public ResponseEntity<MerchantDto> createMerchant(){
        MerchantDto merchantDto = merchantService.createMerchant();
        return ResponseEntity.ok(merchantDto);
    }
}
