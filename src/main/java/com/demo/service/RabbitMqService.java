package com.demo.service;

import java.util.Map;

/**
 * @author Yan
 */
public interface RabbitMqService {

    public String sendMsg(String msg) throws Exception;

    public String sendFanoutMsg(String msg) throws Exception;

    public String sendTopicMsg(String msg, String routingKey) throws Exception;

    public String sendHeaderMsg(String msg, Map<String, Object> map) throws Exception;
}
