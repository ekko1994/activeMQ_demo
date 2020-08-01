package com.alex.activemq.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:58
 */
@Component
@EnableJms
@EnableScheduling
public class Topic_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage(){
        jmsTemplate.convertAndSend(topic, String.format("topic 生产者发送消息: %s", UUID.randomUUID().toString().substring(0, 8)));
        System.out.println("topic 生产者发送消息~!");
    }
}
