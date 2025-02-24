package com.directa24.main.challenge.limepay.exception.handler;

import com.directa24.main.challenge.limepay.exception.InvalidThresholdException;
import com.directa24.main.challenge.limepay.exception.model.ErrorMessage;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(FeignException.class)
    public ErrorMessage feignExceptionHandler() {
        return new ErrorMessage(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "Error connecting to eron client"
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidThresholdException.class)
    public ErrorMessage invalidThresholdHandler(final InvalidThresholdException e) {
        return new ErrorMessage(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                e.getMessage()
        );
    }
}
