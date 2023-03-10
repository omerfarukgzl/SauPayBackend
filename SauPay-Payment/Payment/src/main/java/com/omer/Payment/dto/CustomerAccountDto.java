package com.omer.Payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountDto {
// customer hesabı ve işlemleri listeleniyor
    private String id;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private String customerTC;

    private AccountTransactionDto account;

}
