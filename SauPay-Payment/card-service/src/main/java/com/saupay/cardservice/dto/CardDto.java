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
    private String binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
    private String cardType;
    private String userId;
    private BankDto bankDto;



    //private Set<AccountDto> accountDtos;


}
