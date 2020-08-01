package com.alex.activemq.ack;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/30 - 16:29
 */
public class JmsProduce_ack {

    // activeMQ地址+端口
    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    // 目的地是队列的名字
    public static final String QUEUE_NAME="queue_ack";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 1; i < 8; i++) {
            TextMessage textMessage = session.createTextMessage("message--->" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("发送消息成功~!");
    }
}
