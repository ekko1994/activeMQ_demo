package com.alex.activemq.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:39
 */
@Component
public class Queue_consummer {

    @JmsListener(destination = "${myqueue}")
    public void reieve(TextMessage textMessage) throws JMSException {
        System.out.println( "消费者消费消息: "+ textMessage.getText());
    }
}
