package com.microservice.invoice.client;

import com.microservice.invoice.presentation.dto.client.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-client",url = "localhost:8090/api/v2/clients")
public interface FeignClientClient {

    @GetMapping("/{id}")
    ClientResponseDto findClientById(@PathVariable Long id);

}
