package com.saupay.domainservice.clients.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentCompleteRequest {
    private String paymentToken;
    private String cardNumber;
}
