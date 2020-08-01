package com.alex.activemq.tx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author zhanghao
 * @date 2020/7/30 - 18:50
 */
public class JmsConsumer_tx {

    // activeMQ地址+端口
    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    // 目的地是队列的名字
    public static final String QUEUE_NAME="queue_tx";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener(new MessageListener() {
            int a = 0;
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("消费者收到消息: " + textMessage.getText());
                    if(a == 0){
                        System.out.println("*** commit *** ");
                        session.commit();
                    }
                    if(a == 2){
                        System.out.println("*** rollback ***");
                        session.rollback();
                    }
                    a++;
                } catch (JMSException e) {
                    try {
                        System.out.println("异常啦,消息回滚");
                        session.rollback();
                    } catch (JMSException jmsException) {
                        jmsException.printStackTrace();
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
