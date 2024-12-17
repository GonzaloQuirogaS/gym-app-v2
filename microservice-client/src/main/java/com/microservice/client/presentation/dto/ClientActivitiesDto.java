package com.microservice.client.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientActivitiesDto {

    private Long id;
    private String name;
    private Integer price;
    private List<ClientDto> clients;

}
