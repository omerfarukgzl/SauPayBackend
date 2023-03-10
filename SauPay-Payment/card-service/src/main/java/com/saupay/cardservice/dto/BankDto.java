package com.saupay.cardservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDto {
    private String id;
    private String bankName;
    private Integer bankCode;

}
