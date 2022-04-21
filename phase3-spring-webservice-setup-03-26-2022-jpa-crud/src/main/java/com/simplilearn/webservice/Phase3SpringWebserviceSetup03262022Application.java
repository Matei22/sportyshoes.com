package com.simplilearn.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Phase3SpringWebserviceSetup03262022Application {

	public static void main(String[] args) {
		SpringApplication.run(Phase3SpringWebserviceSetup03262022Application.class, args);
	}

}

// @SpringBootApplication= @ComponentScan + @EnableAutoConfiguration + @Configuration

// @Configuration : To enable Java-based configuration
// @ComponentScan : This annotation enables component-scanning so that the web controller classes 
					// and other components you create will be automatically discovered 
					// and registered as beans in Spring's Application Context.
// @EnableAutoConfiguration :  To enable Spring Boot's auto-configuration feature.
