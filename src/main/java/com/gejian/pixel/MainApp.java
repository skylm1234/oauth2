package com.gejian.pixel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ljb
 * @date 2021年08月27日 9:39
 * @description
 */
@SpringBootApplication
@MapperScan("com.gejian.pixel.mapper")
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
