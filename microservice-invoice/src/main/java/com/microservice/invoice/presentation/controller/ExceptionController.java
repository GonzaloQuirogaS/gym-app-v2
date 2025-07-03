package com.microservice.invoice.presentation.controller;

import com.microservice.invoice.presentation.exception.IdNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice(basePackages = {"com.microservice.invoice.presentation.controller"})
public class ExceptionController {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlerIdNotFoundException(IdNotFoundException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "IdNotFoundException");
        body.put("message", e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handlerException(Exception e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "InternalServerError");
        body.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
