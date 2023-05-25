package com.saupay.cardservice.dto.converter;

import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.dto.CardJoinDto;
import org.springframework.stereotype.Component;

@Component
public class CardDtoJoinConverter {

    public CardJoinDto convert (Object[] result) {
        CardJoinDto dto = new CardJoinDto();
        dto.setCardNumber((String) result[0]);
        dto.setBinNumber((Integer) result[1]);
        dto.setCardHolderName((String) result[2]);
        dto.setCardCvv((String) result[3]);
        dto.setCardExpireDate((String) result[4]);
        dto.setCardType((String) result[5]);
        dto.setBankName((String) result[6]);
        return dto;
    }
}
