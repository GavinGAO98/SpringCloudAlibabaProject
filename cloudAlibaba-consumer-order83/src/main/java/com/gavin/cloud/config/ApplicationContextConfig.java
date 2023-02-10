package com.gavin.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    /**
     * Spring boot 1.4后不自带restTemplate的bean，需要手动添加到容器中
     * @return
     */
    @Bean
    @LoadBalanced // 当调用服务存在集群时，一定要添加这个注解，表示负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
