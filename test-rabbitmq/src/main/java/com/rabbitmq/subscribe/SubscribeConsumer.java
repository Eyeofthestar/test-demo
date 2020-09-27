package com.rabbitmq.subscribe;

import javafx.scene.chart.ValueAxis;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SubscribeConsumer {

    @RabbitListener(
            //            bindings  调用构造器 QueueBinding
            bindings =@QueueBinding(
                    value = @Queue,  //  不指定队列名称及 创建匿名队列
                    exchange = @Exchange(value = "logs",type = "fanout")  //  spring 中队列的类型有生产者确认
        )
    )
    public void Subscribe1(String messages){
        System.out.println("consumer1:"+messages);
    }

    @RabbitListener(
//            bindings  调用构造器 QueueBinding
            bindings =@QueueBinding(
                    value = @Queue,  //  不指定队列名称及 创建匿名队列
                    exchange = @Exchange(value = "logs",type = "fanout")  //  spring 中队列的类型有生产者确认
            )
    )
    public void Subscribe2(String messages){
        System.out.println("consumer2:"+messages);
    }
}
