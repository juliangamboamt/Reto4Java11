package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages={"Controller", "Model", "Repository", "Repository.CrudRepository", "Service"})
@EntityScan("Model")
@EnableJpaRepositories("Repository")
public class Reto3Application {

	public static void main(String[] args) {
		SpringApplication.run(Reto3Application.class, args);
	}

}
