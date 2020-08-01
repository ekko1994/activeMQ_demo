package com.alex.activemq.async;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/30 - 18:50
 */
public class JmsConsumer {

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
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true){
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if (textMessage != null) {
                System.out.println("消费消息: " + textMessage.getText());
            }else{
                break;
            }
        }
        // 9 关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
