package com.saupay.transactionservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Transaction_MerchantDto {
    private String merchantName;
    private BigDecimal amount;
    private LocalDateTime localDateTime;

}
