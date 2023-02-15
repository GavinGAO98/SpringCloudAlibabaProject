package com.gavin.cloud.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("{com.gavin.cloud.dao}")// 告诉Mybatis扫描那个包下的Dao接口
public class MybatisConfig {
}
