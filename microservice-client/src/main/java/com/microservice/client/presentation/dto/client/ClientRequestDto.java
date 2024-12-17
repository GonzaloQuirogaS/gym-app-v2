package com.microservice.client.presentation.dto.client;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequestDto {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private Long phone;
}
