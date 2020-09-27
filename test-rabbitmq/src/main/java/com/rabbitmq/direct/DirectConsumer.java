package com.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "driects",type = "direct"),
//                    设置路由 key
                    key = {"hello","word"}
            )
    )
    public void direct1(String message){
        System.out.println("direct1:"+message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "driects",type = "direct"),
//                    设置路由 key
                    key = {"word"}
            )
    )
    public void direct2(String message){
        System.out.println("direct2:"+message);
    }
}
