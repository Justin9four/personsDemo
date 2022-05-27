package com.chandler.demo.exception;

import com.chandler.demo.controller.dtos.DataConflictErrorDto;
import com.chandler.demo.controller.dtos.ValidationErrorDto;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ValidationErrorDto> handleValidation(ValidationException ex) {
        return new ResponseEntity<>(ex.getValidationErrorDto(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataConflictException.class})
    public ResponseEntity<DataConflictErrorDto> handleDataConflict(DataConflictException ex) {
        return new ResponseEntity<>(ex.getDataConflictErrorDto(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({PSQLException.class})
    public ResponseEntity<DataConflictErrorDto> handleDataConflict(PSQLException ex) {
        return new ResponseEntity<>(new DataConflictErrorDto(ex.getMessage()), HttpStatus.CONFLICT);
    }
}
