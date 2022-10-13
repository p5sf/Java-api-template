package com.demo.listener;

import com.demo.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author YanZhao
 * @description 一对一监听路由信息
 * @date 2022年09月21日 16:42
 */

@Component
@RabbitListener(queues = RabbitConstant.RABBITMQ_DEMO_DIRECT)
public class RabbitMqDirectListener {


    @RabbitHandler
    public void accept(Map map) {
        System.out.println("一对一接收消息:" + map.toString());

    }

}
