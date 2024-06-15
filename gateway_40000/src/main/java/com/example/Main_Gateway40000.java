package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class Main_Gateway40000 {
    public static void main(String[] args) {
        SpringApplication.run(Main_Gateway40000.class,args);
    }
}