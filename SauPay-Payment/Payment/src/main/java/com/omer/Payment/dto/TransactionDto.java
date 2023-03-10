package com.omer.Payment.dto;

import com.omer.Payment.model.Account;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private String id;
    private BigDecimal amaount;
    private LocalDateTime localDateTime;
    private Commerce_CompanyDto commerce_companyDto;
}
