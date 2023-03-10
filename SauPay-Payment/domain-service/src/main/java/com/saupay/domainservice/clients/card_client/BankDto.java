package com.saupay.domainservice.clients.card_client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
    private String id;
    private String bankName;
    private Integer bankCode;
}
