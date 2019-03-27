package com.wxapp.shopapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxapp.shopapp.dao")
public class ShopappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopappApplication.class, args);
    }
}
