package com.example.mainmicroservicedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MainMicroserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainMicroserviceDemoApplication.class, args);
    }

}
