package com.microservice.invoice.service.client;

import com.microservice.invoice.presentation.dto.activity.ActivityResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-activity",url = "localhost:9090/api/v2/activities")
public interface FeignClientServiceActivity {
    @GetMapping("/{id}")
    ActivityResponseDto findActivityById(@PathVariable Long id);
}
