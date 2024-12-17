package com.microservice.client.client;

import com.microservice.client.presentation.dto.activity.ActivityResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "msvc-activity",url = "localhost:9090/api/v2/activities")
public interface FeingClient {

    @GetMapping("/{id}")
    ActivityResponseDto findActivityById(@PathVariable Long id);

}
