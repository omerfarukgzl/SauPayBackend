package com.saupay.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class GeneralException extends RuntimeException{
    private String errorCode;
    private String errorDescription;

}
