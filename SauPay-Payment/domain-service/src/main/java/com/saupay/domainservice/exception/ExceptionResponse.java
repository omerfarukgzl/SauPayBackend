package com.saupay.domainservice.exception;

import com.saupay.domainservice.response.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse <T> {
    //@JsonUnwrapped
/*    private T data;*/
    private Status status;

    public ExceptionResponse( Boolean success, String errorCode, String errorDescription) {
        this.status = new Status(success,errorCode,errorDescription);
    }

}
