package com.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// @RabbitListener  声明是一个 消费者
//@Queue("hello")  创建名为 hello 的队列
// 隐藏默认值  持久化  不独占  不自动删除队列
// 类似   channel.queueDeclare("test",false,false,false,null);
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {

//    @RabbitHandler  声明是回调方法
//    message  接受回调内容
    @RabbitHandler
    public void message(String message){
        System.out.println("message:"+message);
    }
}
