package com.alex.activemq.redelivery;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/30 - 18:50
 */
public class JmsConsumer_Redelivery {

    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    public static final String QUEUE_NAME="queue-redelivery";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 修改默认参数，设置消息消费重试3次
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
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
//        session.commit();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
