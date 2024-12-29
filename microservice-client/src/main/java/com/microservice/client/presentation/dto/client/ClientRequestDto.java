package com.microservice.client.presentation.dto.client;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private Integer age;
    @NotBlank
    private String email;
    @NotNull
    private Long phone;
}
