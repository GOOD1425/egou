package com.zbdx.egou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zbdx.egou.dao")
@SpringBootApplication
public class EgouApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgouApplication.class, args);
    }

}
