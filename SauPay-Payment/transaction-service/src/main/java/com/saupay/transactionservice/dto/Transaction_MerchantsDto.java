package com.saupay.transactionservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_MerchantsDto {
    private List<Transaction_MerchantDto> transactions;

}
