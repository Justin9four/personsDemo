package com.chandler.demo.exception;

import com.chandler.demo.controller.dtos.DataConflictErrorDto;

public class DataConflictException extends Exception {
    private DataConflictErrorDto dataConflictErrorDto;


    public DataConflictException(String dataConflictError) {
        super(dataConflictError);
        this.dataConflictErrorDto = new DataConflictErrorDto(dataConflictError);
    }

    public DataConflictErrorDto getDataConflictErrorDto() {
        return dataConflictErrorDto;
    }

    public void setDataConflictErrorDto(DataConflictErrorDto dataConflictErrorDto) {
        this.dataConflictErrorDto = dataConflictErrorDto;
    }
}
