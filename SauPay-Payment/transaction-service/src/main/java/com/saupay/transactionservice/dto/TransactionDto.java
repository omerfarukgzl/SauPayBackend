package com.saupay.transactionservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private String id;
    private BigDecimal amount;
    private LocalDateTime localDateTime;

}
