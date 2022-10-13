package com.demo.listener;

import com.demo.constant.RabbitConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author YanZhao
 * @description 主题交换机 匹配路由键发送消息
 * @date 2022年09月21日 16:44
 */

@Component
public class RabbitMqTopicListener {


    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.TOPIC_EXCHANGE_QUEUE_A))
    public void processA(Map<String, Object> map) {
        System.out.println("topic队列A[" + RabbitConstant.TOPIC_EXCHANGE_QUEUE_A + "]收到消息：" + map.toString());
    }


    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.TOPIC_EXCHANGE_QUEUE_B))
    public void processB(Map<String, Object> map) {
        System.out.println("topic队列B[" + RabbitConstant.TOPIC_EXCHANGE_QUEUE_B+ "]收到消息：" + map.toString());
    }


    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.TOPIC_EXCHANGE_QUEUE_C))
    public void process(Map<String, Object> map) {
        System.out.println("topic队列C[" + RabbitConstant.TOPIC_EXCHANGE_QUEUE_C + "]收到消息：" + map.toString());
    }

}


