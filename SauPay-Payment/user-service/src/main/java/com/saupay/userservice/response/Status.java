package com.saupay.userservice.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Status {
    private boolean success;
    private String errorDescription;
    private String errorCode;
}
