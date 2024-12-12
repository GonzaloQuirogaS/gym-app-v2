package com.micorservice.gateway.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gym management",
                version = "2.0.0",
                description = "Gym management application"

        )
)
public class OpenApiConfig {
}