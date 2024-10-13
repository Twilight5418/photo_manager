package com.example.photomanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.photomanager.mapper")  // 确保扫描到 Mapper 包
public class PhotoManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhotoManagerApplication.class, args);
    }
}
