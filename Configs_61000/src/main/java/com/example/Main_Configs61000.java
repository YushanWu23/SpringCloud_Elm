package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Main_Configs61000 {
    public static void main(String[] args) {
        SpringApplication.run(Main_Configs61000.class,args);
    }
}