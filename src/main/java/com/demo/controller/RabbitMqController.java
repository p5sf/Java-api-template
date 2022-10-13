package com.demo.controller;

import com.demo.service.impl.RabbitMqServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author YanZhao
 * @description
 * @date 2022年09月20日 15:07
 */

@RestController
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMqServiceImpl rabbitMqService;


    /**
     * 一对一发送消息
     *
     */
    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMqService.sendMsg(msg);
    }

    /**
     * 一对多发送消息 fanout 一个发送消息都会被转发到与改交换机绑定上所有队列上
     */
    @GetMapping("/publish")
    public String publish(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMqService.sendFanoutMsg(msg);
    }

    /**
     * 主题交换机 通配符匹配路由到对应的队列 * 匹配一个、 # 匹配多个
     * topicSend?msg=4565&routingKey=a.b
     * topicSend?msg=4565&routingKey=rabbit.a
     */

    @GetMapping("/topicSend")
    public String topicSend(@RequestParam(name = "msg") String msg, @RequestParam(name = "routingKey") String routingKey) throws Exception {
        return rabbitMqService.sendTopicMsg(msg, routingKey);
    }

    /**
     * 头部交换机 路由不是由routingKey 进行路由匹配，而是由匹配请求头所带的键值进行路由
     */
    @PostMapping("headSend")
    public String headersSend(@RequestParam(name = "msg") String msg,
                              @RequestParam(name = "json") String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        return rabbitMqService.sendHeaderMsg(msg, map);
    }





}
