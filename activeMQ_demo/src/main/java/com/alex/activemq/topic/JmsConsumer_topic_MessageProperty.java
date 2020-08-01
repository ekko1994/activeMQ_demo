package com.alex.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author zhanghao
 * @date 2020/7/30 - 19:55
 */
public class JmsConsumer_topic_MessageProperty {

    public static final String ACTIVEMQ_URL = "tcp://192.168.44.151:61616";

    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Topic topic = session.createTopic(TOPIC_NAME);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        messageConsumer.setMessageListener(message -> {
            if (message != null && message instanceof TextMessage) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("*** 消费者消费TOPIC_NAME消息: *** " + textMessage.getText());

                    boolean flag = textMessage.getBooleanProperty("flag");
                    byte aByte = textMessage.getByteProperty("byte");
                    String k1 = textMessage.getStringProperty("k1");

                    System.out.println("消息属性k1 = " + k1 + ", byte = " + aByte + ", flag = " + flag);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
