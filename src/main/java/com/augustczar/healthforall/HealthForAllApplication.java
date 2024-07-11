package com.augustczar.healthforall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Saúde para Todos", version = "1.0.0", description = "API Cadastro de Beneficíarios"),
servers = {
		@Server(url = "${openapi.server.url}")
})
public class HealthForAllApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HealthForAllApplication.class, args);
	}
}
