package com.directa24.main.challenge.limepay.exception;

import lombok.Data;

@Data
public class InvalidThresholdException extends RuntimeException {
    private String message;

    public InvalidThresholdException(final int threshold) {
        this.message = "Invalid threshold " + threshold;
    }
}
