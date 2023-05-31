package com.saupay.domainservice.clients.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCardRequest {
    private String email;
    private String cardNumber;
    private Integer binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
}
