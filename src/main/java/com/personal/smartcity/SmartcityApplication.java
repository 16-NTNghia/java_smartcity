package com.personal.smartcity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SmartcityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartcityApplication.class, args);
    }

}
