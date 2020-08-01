package com.alex.activemq.nio;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author zhanghao
 * @date 2020/7/31 - 19:08
 */
public class Jms_NIO_Consumer {

//    public static final String ACTIVEMQ_URL = "nio://192.168.44.151:61618";
    public static final String ACTIVEMQ_URL = "tcp://192.168.44.151:61608";

    public static final String QUEUE_NAME = "activemq_nio";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费消息: " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
