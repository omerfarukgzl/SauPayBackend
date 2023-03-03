package com.omer.Payment.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    private BankCompanyDto bankDto;
    private Set<TransactionDto> transactions = new HashSet<>();


    //private Set<AccountDto> accountDtos;


}
