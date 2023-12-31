package com.hungpn.learn.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest req){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(HttpStatus. BAD_REQUEST)
    public ErrorResponse handlerInvalidException(InvalidException ex,WebRequest req){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
