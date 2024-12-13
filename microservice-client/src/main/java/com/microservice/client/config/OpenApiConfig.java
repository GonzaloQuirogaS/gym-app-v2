package com.microservice.client.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gym management",
                version = "2.0.0",
                description = "Gym management application, client service"
        ),
        servers = {
                @Server(url = "http://localhost:8090"),
        }
)
public class OpenApiConfig {
}