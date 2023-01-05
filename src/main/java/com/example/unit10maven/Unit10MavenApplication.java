package com.example.unit10maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.unit10maven")
public class Unit10MavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(Unit10MavenApplication.class, args);


    }

}
