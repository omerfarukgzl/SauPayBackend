package com.saupay.domainservice.clients.user_client;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
// yanlıza customer ve hesabı listeleniyor
    private String id;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private String customerTC;
}
