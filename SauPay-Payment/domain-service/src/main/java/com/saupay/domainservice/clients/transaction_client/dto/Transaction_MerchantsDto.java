package com.saupay.domainservice.clients.transaction_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_MerchantsDto {
    private List<Transaction_MerchantDto> transactions;
}
