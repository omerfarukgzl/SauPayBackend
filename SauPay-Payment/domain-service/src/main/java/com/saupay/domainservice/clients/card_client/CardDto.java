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
    //@JsonIgnore
    private String id;
    private String cardNumber;
    private String binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
    private String cardType;
    private String userId;
    private String bankId;
}
