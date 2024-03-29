package com.saupay.cardservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private String id;
    private String cardNumber;
    private Integer binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
    private String cardType;
    private String userId;
    private Integer bankCode;
    //private Set<AccountDto> accountDtos;


}