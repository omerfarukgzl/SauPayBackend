package com.saupay.domainservice.clients.transaction_client;


import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private BigDecimal amount;
    private LocalDateTime localDateTime;
    private String cardId;
    private String merchantId;
    private String userId;
    private String token;
    private Boolean status;

}