package com.saupay.transactionservice.service;

import com.saupay.transactionservice.dto.MerchantDto;
import com.saupay.transactionservice.dto.converter.MerchantDtoConverter;
import com.saupay.transactionservice.model.Merchant;
import com.saupay.transactionservice.repository.MerchantRepository;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    private final MerchantDtoConverter merchantDtoConverter;

    public MerchantService(MerchantRepository merchantRepository, MerchantDtoConverter merchantDtoConverter) {
        this.merchantRepository = merchantRepository;
        this.merchantDtoConverter = merchantDtoConverter;
    }

    public MerchantDto createMerchant() {
        Merchant merchant = new Merchant("","SauGetir","543454");
       return merchantDtoConverter.convert(merchantRepository.save(merchant));
    }
    public Merchant getMerchant(String code) {
        Merchant merchant = merchantRepository.findByMerchantCode(code).orElseThrow(() -> new RuntimeException("Merchant Company not found"));
        return merchant;
    }
}
