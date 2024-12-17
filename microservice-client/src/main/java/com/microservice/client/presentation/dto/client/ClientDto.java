package com.microservice.client.presentation.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private Long phone;
    private LocalDateTime registerDate = LocalDateTime.now();
    private LocalDateTime activityRegisterDate;
    private LocalDateTime activityExpireDate;

}