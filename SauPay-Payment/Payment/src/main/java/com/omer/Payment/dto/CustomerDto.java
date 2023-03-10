package com.omer.Payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
// yanlıza customer ve hesabı listeleniyor
    private String id;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private String customerTC;

    private AccountDto account;
}
