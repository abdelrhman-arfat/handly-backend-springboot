package com.project.handly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HandlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandlyApplication.class, args);
    }

}
