package com.saupay.domainservice.clients.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    private String paymentToken;
    private String email;
}
