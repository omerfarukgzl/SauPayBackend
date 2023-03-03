package com.omer.Payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankCompanyDto {
    private String id;
    private String bankName;
    private String bankCode;

}
