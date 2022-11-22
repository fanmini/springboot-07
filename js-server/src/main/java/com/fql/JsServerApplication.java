package com.fql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Qian
 * @EnableGlobalMethodSecurity: 开启security注解
 */
@SpringBootApplication
@EnableTransactionManagement
public class JsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsServerApplication.class, args);
    }

}
