package com.alex.activemq.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author zhanghao
 * @date 2020/7/31 - 16:22
 */
@Component
public class Topic_Consumer {

    @JmsListener(destination = "${mytopic}")
    public void reieveTopicMessage(TextMessage textMessage) throws JMSException {
        System.out.println("topic消费者消费消息: " + textMessage.getText());
    }
}
