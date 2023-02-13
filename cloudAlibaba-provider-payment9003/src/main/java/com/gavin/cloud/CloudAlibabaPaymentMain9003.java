package com.gavin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaPaymentMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaPaymentMain9003.class ,args);
    }
}