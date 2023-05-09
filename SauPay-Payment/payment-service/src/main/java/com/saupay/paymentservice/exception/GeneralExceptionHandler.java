package com.saupay.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//bu annotation ile bu class herhangi bir exception firlatirsa bu classin icindeki methodlar calisir
public class GeneralExceptionHandler  {

    @ExceptionHandler(UserNotFoundException.class)//bu annotation ile bu method herhangi bir exception firlatirsa bu method calisir
    public ResponseEntity<?> handleCardNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
