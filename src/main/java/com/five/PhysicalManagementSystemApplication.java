package com.five;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.five.dao")
@EnableTransactionManagement    // 开启事务
@Slf4j
public class PhysicalManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhysicalManagementSystemApplication.class, args);
    }

}
