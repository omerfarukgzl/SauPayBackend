package com.saupay.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//bu annotation ile bu class herhangi bir exception firlatirsa bu classin icindeki methodlar calisir
public class GeneralExceptionHandler  {
    @ExceptionHandler(GeneralException.class)//bu annotation ile bu method herhangi bir exception firlatirsa bu method calisir
    public ResponseEntity<?> handleTransactionNotFoundException(GeneralException exception){

        ExceptionResponse<?> exceptionResponse = new ExceptionResponse<>(false,exception.getErrorCode(),exception.getErrorDescription());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);// 422 status code
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
