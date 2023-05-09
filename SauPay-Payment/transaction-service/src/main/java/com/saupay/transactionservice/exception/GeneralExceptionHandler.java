package com.saupay.transactionservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//bu annotation ile bu class herhangi bir exception firlatirsa bu classin icindeki methodlar calisir
public class GeneralExceptionHandler  {
    @ExceptionHandler(TransacitonNotFoundException.class)//bu annotation ile bu method herhangi bir exception firlatirsa bu method calisir
    public ExceptionResponse<?> handleTransactionNotFoundException(TransacitonNotFoundException exception){
        return new ExceptionResponse<>(exception.getData(), exception.getErrorCode(),exception.getErrorDescription());
    }
}
