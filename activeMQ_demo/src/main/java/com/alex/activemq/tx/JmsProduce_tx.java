package com.alex.activemq.tx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author zhanghao
 * @date 2020/7/30 - 16:29
 */
public class JmsProduce_tx {

    // activeMQ地址+端口
    public static final String ACTIVEMQ_URL="tcp://192.168.44.151:61616";
    // 目的地是队列的名字
    public static final String QUEUE_NAME="queue_tx";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        try {
            for (int i = 1; i < 4; i++) {
                TextMessage textMessage = session.createTextMessage("message--->" + i);
                messageProducer.send(textMessage);
                if( i == 4){
                    throw new RuntimeException("异常啦~!");
                }
            }
            // 需要提交事务才能将这些消息发送出去
            session.commit();
            System.out.println("发送消息成功~!");
        }catch (Exception exception){
            System.out.println("出现异常,消息回滚");
            // 消息回滚
            session.rollback();
        }finally {
            // 9 关闭资源
            messageProducer.close();
            session.close();
            connection.close();
        }
    }
}
