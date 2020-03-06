package com.ly.messagemanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/ly/messagemanage/Mapper")
public class MessagemanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagemanageApplication.class, args);
    }

}
