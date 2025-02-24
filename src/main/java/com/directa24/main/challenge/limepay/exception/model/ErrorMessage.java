package com.directa24.main.challenge.limepay.exception.model;

import lombok.Data;

@Data
public class ErrorMessage {

    private String errorCode;
    private String errorMessage;

    public ErrorMessage(final String errorCode, final String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}