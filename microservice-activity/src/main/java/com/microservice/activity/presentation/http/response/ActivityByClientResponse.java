package com.microservice.activity.presentation.http.response;

import com.microservice.activity.presentation.dto.client.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityByClientResponse {

    private Long id;
    private String name;
    private Integer price;
    private List<ClientDto> clientDtoList;

}
