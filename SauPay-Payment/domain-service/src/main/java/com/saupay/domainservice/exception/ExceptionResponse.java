package com.saupay.domainservice.exception;

import com.saupay.domainservice.response.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse <T> {
    //@JsonUnwrapped
    private T data;
    private Status status;

    public ExceptionResponse(T data,Boolean success, String errorCode, String errorDescription) {
        this.data= data;
        this.status = new Status(success,errorCode,errorDescription);
    }

}
