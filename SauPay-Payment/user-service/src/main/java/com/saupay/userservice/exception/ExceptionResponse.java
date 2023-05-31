package com.saupay.userservice.exception;

import com.saupay.userservice.response.Status;
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
