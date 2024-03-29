package com.saupay.domainservice.clients.card_client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
