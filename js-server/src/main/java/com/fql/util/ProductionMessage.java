package com.fql.util;

import com.fql.entity.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Qian
 */
@Slf4j
@Component
public class ProductionMessage {
    private static final String EXCHANGE_NAME="js.in_home.topic";
    private static final String QUEUE_NAME="queue_customer.all";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 生产消息
    public boolean createMes(Mail msg) {
        CorrelationData correlationData = new CorrelationData(msg.getMesId());
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,
                                      QUEUE_NAME,
                                      msg,
                                      correlationData);
        log.info("消息：{} 生产成功",msg);
        return true ;
    }

}
