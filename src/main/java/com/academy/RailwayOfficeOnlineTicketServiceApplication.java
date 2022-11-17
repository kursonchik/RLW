package com.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"com.academy"})
@EntityScan("com.academy.model")
@EnableJpaRepositories("com.academy.model.repository")
public class RailwayOfficeOnlineTicketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayOfficeOnlineTicketServiceApplication.class, args);
    }

}
