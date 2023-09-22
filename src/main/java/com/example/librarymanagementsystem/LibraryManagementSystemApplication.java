package com.example.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}

// Swagger dependency and how to run:
// -->  https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui/2.1.0
// --> after adding run and the end point is: localhost:8080/swagger-ui/index.html#


// postgres dependency and change url in application properties:
// -->  https://mvnrepository.com/artifact/org.postgresql/postgresql/42.6.0
// -->  spring.datasource.url=jdbc:postgresql://localhost:5432/lmsdb?createTableIfNotExists=true

// add java mail sender dependecy to send email