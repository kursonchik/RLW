package com.academy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class RailwayOfficeOnlineTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayOfficeOnlineTicketServiceApplication.class, args);
	}

}
