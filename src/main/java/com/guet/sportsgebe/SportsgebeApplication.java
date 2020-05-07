package com.guet.sportsgebe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.guet.sportsgebe.dao"})
public class SportsgebeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsgebeApplication.class, args);
    }

}
