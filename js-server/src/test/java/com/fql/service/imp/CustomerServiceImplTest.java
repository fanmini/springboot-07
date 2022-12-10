package com.fql.service.imp;

import com.fql.entity.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 樊乾浪
 * @date 2022/12/7 15:38
 * @company xxx
 */
@SpringBootTest
class CustomerServiceImplTest {
    @Resource
    CustomerServiceImpl c ;

    @Test
    void messagePush() {
        Mail mail = new Mail();
        mail.setTitle("ces");
        mail.setContent("testsssssssssssssssssssssssssssssssss");
        mail.setTo("m14785769048@163.com");
        ArrayList<String> list = new ArrayList<>();
        list.add("m14785769048@163.com");
        mail.setCustomer(list);
        c.messagePush(mail);
    }
}