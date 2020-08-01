package com.alex.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static javax.jms.DeliveryMode.NON_PERSISTENT;

/**
 * @author zhanghao
 * @date 2020/7/30 - 16:29
 */
public class JmsProduce {

    // activeMQ地址+端口
    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
//    public static final String ACTIVEMQ_URL="tcp://localhost:61616";

//    public static final String ACTIVEMQ_URL="failover://(tcp://192.168.44.151:61616,tcp://192.168.44.151:61617,tcp://192.168.44.151:61618)";

    // 目的地是队列的名字
    public static final String QUEUE_NAME="jdbc01";
//    public static final String QUEUE_NAME="queue-cluster";

    public static void main(String[] args) throws JMSException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        for (int i = 1; i < 8; i++) {
            // 7  创建消息
            TextMessage textMessage = session.createTextMessage("message--->" + i);
            // 设置非持久消息
//            messageProducer.setDeliveryMode(NON_PERSISTENT);
            // 设置持久消息
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            // 8  通过messageProducer发送给mq
            messageProducer.send(textMessage);
        }
        // 9 关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("发送消息成功~!");
    }
}
