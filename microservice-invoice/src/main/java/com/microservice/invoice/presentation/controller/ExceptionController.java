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

    @ExceptionHandler(value = {IdNotFoundException.class})
    public ResponseEntity<Object> handlerIdNotFoundException(IdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getErrorMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handlerException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("error: ", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(map);
    }

}
