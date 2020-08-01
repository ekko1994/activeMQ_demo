package com.alex.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhanghao
 * @date 2020/7/31 - 13:37
 */
@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-activemq.xml");
        SpringMQ_Consumer bean = classPathXmlApplicationContext.getBean(SpringMQ_Consumer.class);
        String message = (String) bean.jmsTemplate.receiveAndConvert();
        System.out.println("队列消费者消费消息: " + message);
    }
}
