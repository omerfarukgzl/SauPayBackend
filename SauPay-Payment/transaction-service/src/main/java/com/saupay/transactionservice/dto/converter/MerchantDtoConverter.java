package com.saupay.transactionservice.dto.converter;


import com.saupay.transactionservice.dto.MerchantDto;
import com.saupay.transactionservice.model.Merchant;
import org.springframework.stereotype.Component;

@Component
public class MerchantDtoConverter {

        public MerchantDto convert(Merchant from)
        {
            return new MerchantDto(
                    from.getId(),
                    from.getMerchantName(),
                    from.getMerchantCode()
            );
        }
}
