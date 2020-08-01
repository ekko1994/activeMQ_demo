package com.alex.activemq.config;

import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

/**
 * @author zhanghao
 * @date 2020/7/31 - 16:02
 */
@Configuration
public class MyConfig {

    @Value("${mytopic}")
    private String topicName;

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }
}
