package com.fql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Qian
 * EnableScheduling: 开启任务调用
 */
@SpringBootApplication
@EnableTransactionManagement
public class JsServerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(JsServerApplication.class, args);
    }

}
