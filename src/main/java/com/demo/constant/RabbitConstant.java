package com.demo.constant;

/**
 * @author YanZhao
 * @description RabbitMq常量池
 * @date 2022年09月20日 9:53
 */

public class RabbitConstant {

    /**
     * RabbitMQ的队列主题名称
     */
    public static final String RABBITMQ_DEMO_DIRECT = "rabbitmq.demo.direct";

    /**
     * RabbitMQ的DIRECT交换机名称
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmq.demo.direct.exchange";

    /**
     * RabbitMQ的DIRECT交换机和队列绑定的匹配键 DirectRouting
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmq.demo.direct.routing";





    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 A 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的队列 B 的名称
     */
    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    /**
     * RabbitMQ的FANOUT_EXCHANG交换机类型的名称
     */
    public static final String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo.name";






    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机名称
     */
    public static final String TOPIC_EXCHANGE_DEMO_NAME = "topic.exchange.demo.name";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列A的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_A = "topic.queue.a";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列B的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_B = "topic.queue.b";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列C的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_C = "topic.queue.c";







    /**
     * HEADERS_EXCHANGE交换机名称
     */
    public static final String HEADERS_EXCHANGE_DEMO_NAME = "headers.exchange.demo.name";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列A的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_A = "headers.queue.a";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列B的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_B = "headers.queue.b";



    /**
     * 延迟队列
     */
   public static String DELAY_QUEUE = "delay.queue";

    /**
     * 延迟队列交换器
     */
   public static String DELAY_MODE_QUEUE = "delay.mode";
}
