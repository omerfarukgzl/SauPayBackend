package com.saupay.domainservice.exception;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse <T> {
    @JsonUnwrapped
    private T data;
    private boolean success = false;
    private String errorDescription;
    private String errorCode;

    public ExceptionResponse(T data) {
        this.data= data;
    }

}
