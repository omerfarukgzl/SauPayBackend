package com.saupay.domainservice.clients.transaction_client;

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
    private BigDecimal amaount;
    private LocalDateTime localDateTime;

}