package com.fql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Qian
 */
@SpringBootApplication
@EnableTransactionManagement
public class JsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsServerApplication.class, args);
    }

}
