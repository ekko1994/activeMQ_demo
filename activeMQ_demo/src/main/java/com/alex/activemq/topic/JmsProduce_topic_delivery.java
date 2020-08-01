package com.alex.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static javax.jms.DeliveryMode.PERSISTENT;

/**
 * @author zhanghao
 * @date 2020/7/30 - 19:44
 */
public class JmsProduce_topic_delivery {

    public static final String ACTIVEMQ_URL = "tcp://192.168.44.151:61616";

    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topic);
        // 设置持久化topic
        messageProducer.setDeliveryMode(PERSISTENT);
        // 设置持久化之后再启动连接
        connection.start();

        for (int i = 1; i < 4; i++) {
            TextMessage textMessage = session.createTextMessage("topic message-->" + i);

            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("*** TOPIC_NAME生产消息成功~! ***");
    }
}
