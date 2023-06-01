package com.saupay.domainservice.clients.card_client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {
    private String userId;
    private String cardNumber;
    private String binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
}
