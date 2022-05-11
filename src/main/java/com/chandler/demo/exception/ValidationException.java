package com.chandler.demo.exception;

import com.chandler.demo.controller.dtos.ValidationErrorDto;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {
    private ValidationErrorDto validationErrorDto;

    public ValidationException(ValidationErrorDto validationErrorDto) {
        super(validationErrorDto.toString());
        this.validationErrorDto = validationErrorDto;
    }

    public ValidationException(String validationError) {
        super(validationError);
        List<String> validationErrorList = new ArrayList<>();
        validationErrorList.add(validationError);
        this.validationErrorDto = new ValidationErrorDto(validationErrorList);
    }

    public ValidationErrorDto getValidationErrorDto() {
        return validationErrorDto;
    }

    public void setValidationErrorDto(ValidationErrorDto validationErrorDto) {
        this.validationErrorDto = validationErrorDto;
    }
}
