package com.saupay.transactionservice.exception;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse <T> {

    private boolean success = false;
    private String errorDescription;
    private String errorCode;

    public ExceptionResponse(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
