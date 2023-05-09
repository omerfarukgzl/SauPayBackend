package com.saupay.domainservice.exception;

import com.saupay.domainservice.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//bu annotation ile bu class herhangi bir exception firlatirsa bu classin icindeki methodlar calisir
public class GeneralExceptionHandler  {
    @ExceptionHandler(GeneralException.class)//bu annotation ile bu method herhangi bir exception firlatirsa bu method calisir
    public ExceptionResponse<?> handleTransactionNotFoundException(GeneralException exception){

        ExceptionResponse<?> exceptionResponse = new ExceptionResponse<>(exception.getData());
        exceptionResponse.setErrorCode(exception.getErrorCode());
        exceptionResponse.setErrorDescription(exception.getErrorDescription());

        return exceptionResponse;
    }

 /*   @ExceptionHandler(GeneralException.class)//bu annotation ile bu method herhangi bir exception firlatirsa bu method calisir
    public Response<?> handleTransactionNotFoundException(GeneralException exception){

        Response<?> exceptionResponse = new Response<>(exception.getData());
        exceptionResponse.setErrorCode(exception.getErrorCode());
        exceptionResponse.setErrorDescription(exception.getErrorDescription());
        exceptionResponse.setSuccess(false);

        return exceptionResponse;
    }*/
}
