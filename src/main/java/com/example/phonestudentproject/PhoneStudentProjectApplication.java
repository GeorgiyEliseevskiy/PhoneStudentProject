package com.example.phonestudentproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PhoneStudentProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneStudentProjectApplication.class, args);
    }

}
