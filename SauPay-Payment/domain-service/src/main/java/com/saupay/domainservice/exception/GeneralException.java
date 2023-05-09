package com.saupay.domainservice.exception;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class GeneralException extends RuntimeException{
    private Object data;
    private String errorCode;
    private String errorDescription;

}
