package com.saupay.transactionservice.dto.converter;

import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class Transaction_MerchantDtoConverter {

    public Transaction_MerchantDto convert (Object[] result) {
        Transaction_MerchantDto dto = new Transaction_MerchantDto();
        dto.setMerchantName((String) result[0]);
        dto.setAmount((BigDecimal) result[1]);
        dto.setLocalDateTime((Timestamp) result[2]);
        return dto;
    }

}
