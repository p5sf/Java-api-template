package com.demo.listener;

import com.demo.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author YanZhao
 * @description 广播交换机 一对多交换机
 * @date 2022年09月21日 16:44
 */

@Component
@RabbitListener(queues = RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_A)
public class RabbitMqFanoutListener {

    @RabbitHandler
    public void processA(Map<String, Object> map) {
        System.out.println("队列A收到消息：" + map.toString());
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_B))
    public void processB(Map<String, Object> map) {
        System.out.println("队列B收到消息：" + map.toString());
    }
}
