package com.alex.activemq.async;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * @author zhanghao
 * @date 2020/7/30 - 16:29
 */
public class JmsProduce {

    // activeMQ地址+端口
    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    // 目的地是队列的名字
    public static final String QUEUE_NAME="jdbc01";

    public static void main(String[] args) throws JMSException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 设置异步
        connectionFactory.setUseAsyncSend(true);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的生产者
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer)session.createProducer(queue);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        for (int i = 1; i < 8; i++) {
            // 7  创建消息
            TextMessage textMessage = session.createTextMessage("message--->" + i);
            textMessage.setJMSMessageID(UUID.randomUUID().toString()+"order..");
            String jmsMessageID = textMessage.getJMSMessageID();
            // 8  通过messageProducer发送给mq，并用回调方法接收
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(jmsMessageID + "send ok~!");
                }

                @Override
                public void onException(JMSException exception) {
                    System.out.println(jmsMessageID + "send fail~!");
                }
            });
        }
        // 9 关闭资源
        activeMQMessageProducer.close();
        session.close();
        connection.close();
        System.out.println("发送消息成功~!");
    }
}
