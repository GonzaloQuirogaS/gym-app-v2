package com.microservice.activity.presentation.exception;

import lombok.Getter;

@Getter
public class IdNotFoundException extends RuntimeException{

    private final String errorMessage;

    public IdNotFoundException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
