package com.beanbroker.test.client;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BrokerClient.class)
public class BrokerClientTest {

    @Autowired
    BrokerClient client;


    @Test
    public void testClient(){

        UserResponse userInfo = client.getUserInfo();


        System.out.println(userInfo);
    }
}
