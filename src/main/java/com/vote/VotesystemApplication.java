package com.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class VotesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotesystemApplication.class, args);
    }

}

