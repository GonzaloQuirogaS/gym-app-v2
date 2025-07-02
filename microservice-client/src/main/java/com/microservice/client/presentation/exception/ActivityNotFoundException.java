package com.microservice.client.presentation.exception;

import lombok.Getter;

@Getter
public class ActivityNotFoundException extends RuntimeException {
    private final String errorMessage;

    public ActivityNotFoundException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

