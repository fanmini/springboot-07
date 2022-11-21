package com.fql.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Qian
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class RabbitmqMessageController {
    private static final String EXCHANGE_NAME="fql.in_home.topic";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    @PreAuthorize("hasAnyAuthority('system:admin:all','system:test:query')")
    public String send(String message, String rk){
        // 这里我给三个路由都发消息：rk为：wx.students.all,wx.teachers.all,wx.hr.lida.
        // 这里的exchange名，rk，bk，这些信息在设计阶段最好定好，不要弄太多，
        // 做成枚举或写到配置文件里，避免整个项目里到处都是这些名字的字符串，不理由维护
        log.info("start send message  .... ");
        sendTest1Messages();
        sendTest2Messages();
        sendAllMessages();
        log.info("send message the end .... ");
        return "send message the end ....";
    }

    @Scheduled(fixedRate = 5000)
    private void sendTest1Messages(){
        String message = "只因你太美，ggggggggjntm，实在是太美";
        for(int i = 0; i < 5; i++) {
            if(i % 2 == 0) {
                String uuid = UUID.randomUUID().toString();
                message = uuid + ":ONE:" + message;
                // p1:交换器 name,p2:routing key,p3:消息对象
                rabbitTemplate.convertAndSend(EXCHANGE_NAME,"fql.test1.all",message);
                log.info("test1Messages=> i:{}, message:{}", i, message);
            }
        }
    }

    private void sendTest2Messages() {
        String message = "因为疫情，万息全体老师居家3天，期间注意安排自己各个班级的学员上课情况，考勤不变";
        for(int i = 0; i < 5; i++) {
            if(i % 4 == 0) {
                String uuid = UUID.randomUUID().toString();
                message = uuid + ":TWO:" + message;
                rabbitTemplate.convertAndSend(EXCHANGE_NAME,"fql.test2.all",message);

                log.info("test2Messages=> i:{}, message:{}", i, message);
            }
        }
    }

    private void sendAllMessages(){
        String message = "万息人事注意，疫情全公司都居家办公，注意随时接收政府的新情况";
        for(int i = 0; i < 5; i++) {
            if(i % 3 == 0) {
                String uuid = UUID.randomUUID().toString();
                message = uuid + ":THREE:" + message;
                rabbitTemplate.convertAndSend(EXCHANGE_NAME,"fql.all.lida",message);
                log.info("allMessages=> i:{}, message:{}", i, message);
            }
        }
    }



}
