package com.saupay.userservice.service;

import com.saupay.userservice.dto.MerchantDto;
import com.saupay.userservice.dto.converter.MerchantDtoConverter;
import com.saupay.userservice.model.Merchant;
import com.saupay.userservice.repository.MerchantRepository;
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
    public Merchant getMerchant(String id) {
        Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> new RuntimeException("Merchant Company not found"));
        return merchant;
    }
}
