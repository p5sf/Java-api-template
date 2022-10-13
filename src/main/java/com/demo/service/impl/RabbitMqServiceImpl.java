package com.demo.service.impl;

import com.demo.constant.RabbitConstant;
import com.demo.service.RabbitMqService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月20日 17:35
 */

@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
     * 一对一发送消息
     * @param msg
     * @return
     * @throws Exception
     */
    @Override
    public String sendMsg(String msg) throws Exception {
        try {
            Map<String, Object> message = getMessage(msg);
            rabbitTemplate.convertAndSend(RabbitConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, RabbitConstant.RABBITMQ_DEMO_DIRECT_ROUTING, message);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 一对多发送消息
     * @param msg
     * @return
     * @throws Exception
     */
    @Override
    public String sendFanoutMsg(String msg) throws Exception {
        Map<String, Object> message = getMessage(msg);
        try {
            rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE_DEMO_NAME, "", message);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 匹配路由消息 topic 主题交换机
     * @param msg
     * @return
     * @throws Exception
     */
    @Override
    public String sendTopicMsg(String msg,String routingKey ) throws Exception {
        Map<String, Object> message = getMessage(msg);
        try {
            rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE_DEMO_NAME, routingKey, message);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 匹配头部消息 头部交换机
     * @param msg
     * @return
     * @throws Exception
     */
    @Override
    public String sendHeaderMsg(String msg,Map<String, Object> map) throws Exception {
        try {
            MessageProperties messageProperties = new MessageProperties();
            //消息持久化
            messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            messageProperties.setContentType("UTF-8");
            //添加消息
            messageProperties.getHeaders().putAll(map);
            Message message = new Message(msg.getBytes(), messageProperties);
            rabbitTemplate.convertAndSend(RabbitConstant.HEADERS_EXCHANGE_DEMO_NAME, null, message);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    private Map<String, Object> getMessage(String msg) {
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String sendTime = sdf.format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);
        return map;
    }
}
