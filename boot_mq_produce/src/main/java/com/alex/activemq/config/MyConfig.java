package com.alex.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Destination;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:11
 */
@Configuration
public class MyConfig {

    @Value("${myqueue}")
    private String queueName;

    @Bean
    public ActiveMQQueue activeMQQueue(){
        return new ActiveMQQueue(queueName);
    }
}
