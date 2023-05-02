package com.saupay.transactionservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionsDto {
    List<TransactionDto> transactions;
}
