package com.exemple.supercopoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.exemple.supercopoapi.config.property.SuperCopoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(SuperCopoApiProperty.class)
public class SupercopoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupercopoApiApplication.class, args);
	}

}
