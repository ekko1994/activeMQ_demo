package com.alex.activemq.delayandschedule;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/30 - 16:29
 */
public class JmsProduce_DelayAndSchedule {

    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    public static final String QUEUE_NAME="jdbc01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        // 延时投递时间
        long delay = 3 * 1000;
        // 时间周期
        long period = 4 * 1000;
        // 重复几次
        int repeat = 5;

        for (int i = 1; i < 8; i++) {
            TextMessage textMessage = session.createTextMessage("delay message--->" + i);

            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);

            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("发送消息成功~!");
    }
}
