package com.micorservice.gateway;

import io.netty.handler.codec.http.HttpMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/api/v2/activities").and().method(String.valueOf(HttpMethod.GET)).uri("http://localhost:9090"))
				.route(r -> r.path("/api/v2/clients").and().method(String.valueOf(HttpMethod.GET)).uri("http://localhost:8090"))
				.build();
	}


}
