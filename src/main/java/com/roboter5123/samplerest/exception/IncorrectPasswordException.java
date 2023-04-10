package com.roboter5123.samplerest.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String message) {

        super(message);
    }
}
