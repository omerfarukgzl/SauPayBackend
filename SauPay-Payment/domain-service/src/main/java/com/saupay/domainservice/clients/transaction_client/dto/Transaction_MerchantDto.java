package com.saupay.domainservice.clients.transaction_client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction_MerchantDto implements Comparable<Transaction_MerchantDto>{
    private String merchantName;
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private Timestamp localDateTime;

    @Override
    public int compareTo(Transaction_MerchantDto o) {
        return this.localDateTime.compareTo(o.localDateTime);
    }

}