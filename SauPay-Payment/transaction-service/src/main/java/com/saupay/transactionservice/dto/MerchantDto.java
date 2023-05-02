package com.saupay.transactionservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantDto {
    private String id;
    private String merchantName;
    private String merchantCode;
}
