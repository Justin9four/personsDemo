package com.chandler.demo.controller.dtos;

public class DataConflictErrorDto {

    private final String dataConflictMessage;

    public DataConflictErrorDto(String dataConflictMessage) {
        this.dataConflictMessage = dataConflictMessage;
    }

    public String getErrorType() {
        return "Data Conflict Error";
    }

    public String getDataConflictMessage() {
        return dataConflictMessage;
    }
}
