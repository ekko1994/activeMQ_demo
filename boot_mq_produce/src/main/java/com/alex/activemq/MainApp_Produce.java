package com.alex.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:21
 */
@SpringBootApplication
@EnableJms
public class MainApp_Produce {

    public static void main(String[] args) {
        SpringApplication.run(MainApp_Produce.class, args);
    }
}
