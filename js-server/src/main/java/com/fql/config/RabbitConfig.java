package com.fql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Qian
 */
@Configuration
@Slf4j
public class RabbitConfig {
    private static final String TOPIC_EXCHANGE="fql.in_home.topic";

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue queueTestAll(){
        log.info("创建队列queueTestAll...");
        return new Queue("fql_all",true);
    }

    @Bean
    public Queue queueTest1(){
        log.info("创建队列queueTest1...");
        return new Queue("fql_test1",true);
    }

    @Bean
    public Queue queueTest2(){
        log.info("创建队列queueTest2...");
        return new Queue("fql_test2",true);
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        log.info("topic 实例化主题交换机,name:{}",TOPIC_EXCHANGE);
        return new TopicExchange(TOPIC_EXCHANGE,true,false);
    }

    /**
     * 绑定队列
     */
    @Bean
    public Binding bindingTestAll(){
        log.info("队列all绑定交换机,name:{}",TOPIC_EXCHANGE);
        return BindingBuilder.bind(queueTestAll()).to(topicExchange()).with("fql.#");
    }

    @Bean
    public Binding bindingTest1(){
        log.info("队列test1绑定交换机,name:{}",TOPIC_EXCHANGE);
        return BindingBuilder.bind(queueTest1()).to(topicExchange()).with("fql.test1.*");
    }

    @Bean
    public Binding bindingTest2(){
        log.info("队列test2绑定交换机,name:{}",TOPIC_EXCHANGE);
        return BindingBuilder.bind(queueTest2()).to(topicExchange()).with("fql.test2.*");
    }

}
