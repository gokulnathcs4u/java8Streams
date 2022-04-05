package com.java8streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Java8streamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java8streamsApplication.class, args);
	}

}
