package com.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                value = @Queue,
                exchange = @Exchange(value = "topics",type = "topic"),
                key = "hello.#"
    ))
    public void consumer01(String message){
        System.out.println("consumer01:----"+message);
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics",type = "topic"),
                    key = "hello.*"
            ))
    public void consumer02(String message){
        System.out.println("consumer02:----"+message);
    }
}
