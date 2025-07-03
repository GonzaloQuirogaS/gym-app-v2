package com.microservice.client.util;

import com.microservice.client.persistence.entity.Client;
import com.microservice.client.presentation.dto.client.ClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class Utils {
    public ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    public ClientDto mapToDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .age(client.getAge())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registerDate(client.getRegisterDate())
                .idActivity(client.getActivityId())
                .activityRegisterDate(client.getActivityRegisterDate())
                .activityExpireDate(client.getActivityExpireDate())
                .build();
    }

}
