package com.gavin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaPaymentMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaPaymentMain9004.class ,args);
    }
}