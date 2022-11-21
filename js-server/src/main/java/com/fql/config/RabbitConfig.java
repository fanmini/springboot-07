package com.fql.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Qian
 */
@Configuration
@Slf4j
public class RabbitConfig {
    private static final String TOPIC_EXCHANGE="js.in_home.topic";

    @Resource
    CachingConnectionFactory connectionFactory ;

    // 提供自定义 rabbitmqTemplate
    @Bean
    public RabbitTemplate jacksonRabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return  template ;
    }


    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue queueCustomer(){
        log.info("创建队列----->queue_customer...");
        return new Queue("queue_customer",true);
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        log.info("topic 实例化主题交换机----->{}",TOPIC_EXCHANGE);
        return new TopicExchange(TOPIC_EXCHANGE,true,false);
    }

    /**
     * 绑定队列
     */
    @Bean
    public Binding bindingTest1(){
        log.info("队列queue_customer绑定交换机----->{}",TOPIC_EXCHANGE);
        return BindingBuilder.bind(queueCustomer()).to(topicExchange()).with("queue_customer.*");
    }


}
