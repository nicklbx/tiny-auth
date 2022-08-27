package com.victor.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.victor.auth.dao.mapper")
public class TinyAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyAuthApplication.class, args);
    }

}
