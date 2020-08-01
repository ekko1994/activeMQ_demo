package com.alex.activemq;

import com.alex.activemq.component.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author zhanghao
 * @date 2020/7/31 - 15:28
 */
@SpringBootTest(classes = MainApp_Produce.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {

    @Autowired
    private Queue_Produce queue_produce;

    @Test
    public void testProduce(){
        queue_produce.sendMessage();
    }

}
