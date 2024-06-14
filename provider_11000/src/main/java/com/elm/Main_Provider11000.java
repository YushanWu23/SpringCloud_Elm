package com.elm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Main_Provider11000 {
    public static void main(String[] args) {
        SpringApplication.run(Main_Provider11000.class,args);
    }
}