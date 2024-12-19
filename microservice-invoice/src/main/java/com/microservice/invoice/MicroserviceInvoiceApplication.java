package com.microservice.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceInvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInvoiceApplication.class, args);
	}

}
