package com.alex.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author zhanghao
 * @date 2020/7/30 - 19:55
 */
public class JmsConsumer_topic_delivery {

    public static final String ACTIVEMQ_URL = "tcp://192.168.44.151:61616";

    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("myId");
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Topic topic = session.createTopic(TOPIC_NAME);
        // 5 创建主题订阅者, 第一个参数是topic, 第二个参数是订阅者的名字
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark");
        connection.start();

        Message message = topicSubscriber.receive();
        while (message != null){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("订阅者消费消息: " + textMessage.getText());

            message = topicSubscriber.receive();
        }
        topicSubscriber.close();
        session.close();
        connection.close();
    }
}
