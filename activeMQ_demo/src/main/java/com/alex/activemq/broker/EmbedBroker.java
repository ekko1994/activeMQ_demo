package com.alex.activemq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @author zhanghao
 * @date 2020/7/31 - 11:26
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        //ActiveMQ也支持在vm中通信基于嵌入的broker
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.setUseJmx(true);
        brokerService.start();
    }
}
