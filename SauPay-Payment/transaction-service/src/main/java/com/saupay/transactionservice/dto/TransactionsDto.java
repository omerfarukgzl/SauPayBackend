package com.saupay.transactionservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsDto {
    private List<TransactionDto> transactions;
}
