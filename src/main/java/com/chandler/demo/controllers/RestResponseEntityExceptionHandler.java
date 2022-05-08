package com.chandler.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.EmptyResultDataAccessException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleNotFound(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
