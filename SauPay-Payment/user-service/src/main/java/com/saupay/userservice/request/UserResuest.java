package com.saupay.userservice.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResuest {
    @NotBlank(message = "User name cannot be empty")
    private String userName;
    @NotBlank(message = "User surname cannot be empty")
    private String userSurname;
    @NotBlank(message = "User email cannot be empty")
    private String userEmail;
    @NotBlank(message = "User password cannot be empty")
    private String userPassword;
    @NotBlank(message = "User TC cannot be empty")
    private String userTC;
    @NotBlank(message = "User phone cannot be empty")
    private String userPhone;

}
