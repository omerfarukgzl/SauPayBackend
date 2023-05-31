package com.saupay.userservice.request;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    @NotBlank(message = "User email cannot be empty")
    private String userEmail;
    @NotBlank(message = "User password cannot be empty")
    private String userPassword;
    @NotBlank(message = "User name cannot be empty")
    private String userName;
    @NotBlank(message = "User surname cannot be empty")
    private String userSurname;
    @NotBlank(message = "User phone cannot be empty")
    private String userPhone;
    @NotBlank(message = "User TC cannot be empty")
    private String userTC;

}
