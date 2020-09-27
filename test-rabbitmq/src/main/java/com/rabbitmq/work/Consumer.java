package com.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//    模拟多个消费者

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void work1(String message){
        System.out.println("work1:"+message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void work2(String message){
        System.out.println("work2:"+message);
    }
}
