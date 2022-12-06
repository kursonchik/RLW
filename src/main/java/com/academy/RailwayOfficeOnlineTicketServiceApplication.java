package com.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@EnableJpaRepositories
@SpringBootApplication
//@ComponentScan({"com.academy"})
public class RailwayOfficeOnlineTicketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailwayOfficeOnlineTicketServiceApplication.class, args);
    }

}
