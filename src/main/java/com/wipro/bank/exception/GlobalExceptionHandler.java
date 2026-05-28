package com.wipro.bank.exception;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {

        return "Something went wrong ";
    }
}
