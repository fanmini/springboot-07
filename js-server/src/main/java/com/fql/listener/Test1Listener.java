package com.fql.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Qian
 * 接受消费队列fql_test1信息
 */
@Slf4j
@Component
public class Test1Listener {
    @RabbitListener(queues = {"fql_test1"})
    public void receive(String message){
        log.info("test1:{} 接受到的消息为",message);
    }
}
