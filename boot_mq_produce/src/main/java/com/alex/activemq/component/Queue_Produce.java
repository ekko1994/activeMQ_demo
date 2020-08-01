package com.alex.activemq.component;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.Random;
import java.util.UUID;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:22
 */
@Component
public class Queue_Produce {

    @Autowired
    private ActiveMQQueue activeMQQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(){
        jmsTemplate.convertAndSend(activeMQQueue, UUID.randomUUID().toString().substring(0, 8));
        System.out.println("发布消息~!");
    }

}
