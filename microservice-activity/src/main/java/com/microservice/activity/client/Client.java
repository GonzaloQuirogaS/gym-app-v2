package com.microservice.activity.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-client",url = "localhost:8090/api/v2/clients")
public interface Client {

    @GetMapping("/search-by-activity/{id}")
    List<?> findAllClientsByActivity(@PathVariable Long id);

}
