package com.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
	    info = @Info(title = "My Swagger API", version = "v1", description = "API documentation")
	)



@SpringBootApplication
public class MycrudwithswaggerApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MycrudwithswaggerApplication.class, args);
	}
	


}
