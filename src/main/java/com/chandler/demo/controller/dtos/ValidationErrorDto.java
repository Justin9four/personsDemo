package com.chandler.demo.controller.dtos;

import java.util.List;

public class ValidationErrorDto {

    private final List<String> validationMessages;

    public ValidationErrorDto(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    public String getErrorType() {
        return "Validation Error";
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }
}
