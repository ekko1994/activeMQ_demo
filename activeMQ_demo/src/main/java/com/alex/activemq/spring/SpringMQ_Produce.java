package com.alex.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author zhanghao
 * @date 2020/7/31 - 13:36
 */
@Component
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-activemq.xml");
        SpringMQ_Produce springMQ_produce = classPathXmlApplicationContext.getBean(SpringMQ_Produce.class);
        springMQ_produce.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("*** spring 整合 ActivMQ消息 *** ");
            }
        });
        System.out.println("spring 整合 ActiveMQ消息发布成功~!");
    }
}
