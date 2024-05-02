package com.example.phonestudentproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties
public class PhoneStudentProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneStudentProjectApplication.class, args);
    }

}
