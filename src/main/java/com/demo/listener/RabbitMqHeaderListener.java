package com.demo.listener;

import com.demo.constant.RabbitConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author YanZhao
 * @description 头部交换机 匹配头部发送消息
 * @date 2022年09月21日 16:45
 */

@Component
public class RabbitMqHeaderListener {

    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_A))
    public void processA(Message message) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("队列[" + RabbitConstant.HEADERS_EXCHANGE_QUEUE_A + "]收到消息：" + new String(message.getBody(), contentType));
    }


    @RabbitListener(queuesToDeclare = @Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_B))
    public void processB(Message message) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        System.out.println("队列[" + RabbitConstant.HEADERS_EXCHANGE_QUEUE_B + "]收到消息：" + new String(message.getBody(), contentType));
    }
}
