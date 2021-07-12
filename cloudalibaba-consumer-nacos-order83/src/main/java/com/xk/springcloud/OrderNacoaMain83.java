package com.xk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacoaMain83 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacoaMain83.class,args);
    }
}
