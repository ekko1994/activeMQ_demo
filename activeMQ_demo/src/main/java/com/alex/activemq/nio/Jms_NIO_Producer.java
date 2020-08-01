package com.alex.activemq.nio;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/31 - 19:08
 */
public class Jms_NIO_Producer {

//    public static final String ACTIVEMQ_URL = "nio://192.168.44.151:61618";
    public static final String ACTIVEMQ_URL = "tcp://192.168.44.151:61608";

    public static final String QUEUE_NAME = "activemq_nio";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("生产消息: " + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
    }
}
