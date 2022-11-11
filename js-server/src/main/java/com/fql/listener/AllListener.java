package com.fql.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Qian
 * 监听fql_all的队列消息
 */
@Slf4j
@Component
public class AllListener {
    @RabbitListener(queues = {"fql_all"})
    public void receive(String message, @Payload String payload , @Headers Map headers ){
        log.info("payload: {}" , payload);
        log.info("headers: {}", headers);
        log.info("fql_all 收到的消息为: {}", message);
    }

}
