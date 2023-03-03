package com.omer.Payment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commerce_CompanyDto {
    private String id;
    private String companyName;
    private String companyCode;
}
