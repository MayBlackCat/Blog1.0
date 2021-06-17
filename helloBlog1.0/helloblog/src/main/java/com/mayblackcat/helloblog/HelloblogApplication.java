package com.mayblackcat.helloblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//禁止自动配置数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.mayblackcat.helloblog") //扫描包
public class HelloblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloblogApplication.class, args);
    }

}
