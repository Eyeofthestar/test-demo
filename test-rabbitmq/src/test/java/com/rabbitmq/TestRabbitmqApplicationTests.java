package com.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TestRabbitmqApplicationTests {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
//        发布消息
//        convertAndSend(队列名称，消息内容)
        rabbitTemplate.convertAndSend("hello","hello word!");
    }

//    work queue
    @Test
    void  work(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work test");
        }

    }

    //    订阅模式Subscribe
    @Test
    void  Subscribe(){
            rabbitTemplate.convertAndSend("logs","","work test");
    }

    //    路由模式
    @Test
    void direct(){
        rabbitTemplate.convertAndSend("driects","hello","direct test");
    }


    //    topic模式
    @Test
    void topic(){
        rabbitTemplate.convertAndSend("topics","hello.topic.one","direct test");
    }



    @Test
    void tt(){
//        System.out.println(test20());
    }
}
