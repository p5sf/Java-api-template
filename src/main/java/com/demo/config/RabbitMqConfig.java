package com.demo.config;

import com.demo.constant.RabbitConstant;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YanZhao
 * @description RabbitMQ基础配置
 * @date 2022年09月20日 9:52
 */

@Slf4j
@Configuration
public class RabbitMqConfig {

    /**
     * 开启失败回调
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        // 消息发送失败返回队列中，YML需要配置publisher-returns: true
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        // 发送消息确认，YML需要配置publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失发送失败:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

//    @Bean
//    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory factory){
//        SimpleRabbitListenerContainerFactory srlcf = new SimpleRabbitListenerContainerFactory();
//        srlcf.setConnectionFactory(factory);
//        //当消息有异常内容时（例如类型不匹配），将不再重新放入队列，直接丢弃
//        srlcf.setDefaultRequeueRejected(false);
//        //设置消息转为json
//        Jackson2JsonMessageConverter j2jmc = new Jackson2JsonMessageConverter();
//        srlcf.setMessageConverter(j2jmc);
//        return srlcf;
//    }
//
//    @Bean
//    public MessageConverter messageConverter(){
//        Jackson2JsonMessageConverter j2jmc = new Jackson2JsonMessageConverter();
//        return j2jmc;
//    }


    /**
     * 一对一发送消息
     * 直接队列1
     * 参数一：队列名
     * 参数二：是否持久化，丢失的过程只有在内存发送到磁盘时会丢失消息，如果保存到磁盘，重启服务消息不会丢失，但是会影响效率
     * 参数三：是否只能被当前创建的连接使用，并在连接关闭后销毁队列
     * 参数四：是否在队列为空后自动销毁
     * 参数五：其他配置信息
     */
    @Bean
    public Queue rabbitmqDemoDirectQueue() {
        return new Queue(RabbitConstant.RABBITMQ_DEMO_DIRECT, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange() {
        return new DirectExchange(RabbitConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDirect() {
        //链式写法，绑定队列到交换机，并设置匹配键
        return BindingBuilder.bind(rabbitmqDemoDirectQueue()).to(rabbitmqDemoDirectExchange()).with(RabbitConstant.RABBITMQ_DEMO_DIRECT_ROUTING);
    }



    /**
     * 一对多发送消息 fanout
     * 队列A、B
     */
    @Bean
    public Queue fanoutExchangeQueueA() {
        return new Queue(RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_A, true, false, false);
    }

    @Bean
    public Queue fanoutExchangeQueueB() {
        return new Queue(RabbitConstant.FANOUT_EXCHANGE_QUEUE_TOPIC_B, true, false, false);
    }

    @Bean
    public FanoutExchange rabbitmqDemoFanoutExchange() {
        //创建FanoutExchange类型交换机
        return new FanoutExchange(RabbitConstant.FANOUT_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindFanoutA() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(fanoutExchangeQueueA()).to(rabbitmqDemoFanoutExchange());
    }

    @Bean
    public Binding bindFanoutB() {
        //队列B绑定到FanoutExchange交换机
        return BindingBuilder.bind(fanoutExchangeQueueB()).to(rabbitmqDemoFanoutExchange());
    }



    /**
     * 主题交换机 topic 匹配路由
     * 创建队列1、2、3
     */
    @Bean
    public Queue topicExchangeQueueA() {
        return new Queue(RabbitConstant.TOPIC_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueB() {
        return new Queue(RabbitConstant.TOPIC_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueC() {
        return new Queue(RabbitConstant.TOPIC_EXCHANGE_QUEUE_C, true, false, false);
    }

    @Bean
    public TopicExchange rabbitmqDemoTopicExchange() {
        //配置TopicExchange交换机
        return new TopicExchange(RabbitConstant.TOPIC_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindTopicC() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueA()).to(rabbitmqDemoTopicExchange()).with("rabbit.#");
    }

    @Bean
    public Binding bindTopicA() {
        //队列B绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueB()).to(rabbitmqDemoTopicExchange()).with("a.*");
    }

    @Bean
    public Binding bindTopicB() {
        //队列C绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueC()).to(rabbitmqDemoTopicExchange()).with("a.*");
    }


    /**
     * 头部交换机A、B
     */
    @Bean
    public Queue headersQueueA() {
        return new Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue(RabbitConstant.HEADERS_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public HeadersExchange rabbitmqDemoHeadersExchange() {
        return new HeadersExchange(RabbitConstant.HEADERS_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindHeadersA() {
        Map<String, Object> map = new HashMap<>();
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
        //全匹配
        return BindingBuilder.bind(headersQueueA()).to(rabbitmqDemoHeadersExchange()).whereAll(map).match();
    }

    @Bean
    public Binding bindHeadersB() {
        Map<String, Object> map = new HashMap<>();
        map.put("headers_A", "coke");
        map.put("headers_B", "sky");
        //部分匹配
        return BindingBuilder.bind(headersQueueB()).to(rabbitmqDemoHeadersExchange()).whereAny(map).match();
    }


    /**
     * 延迟队列
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitConstant.DELAY_QUEUE, true);
    }

    /**
     * 延迟队列交换器, x-delayed-type 和 x-delayed-message 固定
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitConstant.DELAY_MODE_QUEUE, "x-delayed-message", true, false, args);
    }

    /**
     * 延迟队列绑定自定义交换器
     */
    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitConstant.DELAY_QUEUE).noargs();
    }



}
