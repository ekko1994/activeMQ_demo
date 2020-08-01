package com.alex.activemq.delayandschedule;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/30 - 18:50
 */
public class JmsConsumer_DelayAndSchedule {

    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    public static final String QUEUE_NAME="jdbc01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
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
