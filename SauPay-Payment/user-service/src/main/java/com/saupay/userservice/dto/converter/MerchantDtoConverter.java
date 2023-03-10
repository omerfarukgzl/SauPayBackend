package com.saupay.userservice.dto.converter;


import com.saupay.userservice.dto.MerchantDto;
import com.saupay.userservice.model.Merchant;
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
