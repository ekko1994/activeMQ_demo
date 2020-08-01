package com.alex.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:37
 */
@SpringBootApplication
public class MainApp_Consumer {

    public static void main(String[] args) {
        SpringApplication.run(MainApp_Consumer.class, args);
    }
}
