package com.xk.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.xk.springcloud.dao"})
public class MyBatisConfig {
}
