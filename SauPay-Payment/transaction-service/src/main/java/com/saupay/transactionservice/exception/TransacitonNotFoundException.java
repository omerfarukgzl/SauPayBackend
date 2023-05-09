package com.saupay.transactionservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Setter
@Getter
public class TransacitonNotFoundException extends RuntimeException{
    private Object data;
    private String errorCode;
    private String errorDescription;

}
