package com.fql.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Qian
 * 接受队列 test2 的消息
 */
@Slf4j
@Component
public class Test2Listener {
    @RabbitListener(queues = {"fql_test2"})
    public void receive(String message){
        log.info("队列test2发出的消息为： {}",message);
    }


}
