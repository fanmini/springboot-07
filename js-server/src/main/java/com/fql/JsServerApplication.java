package com.fql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Qian
 * @EnableGlobalMethodSecurity: 开启security注解
 */
@SpringBootApplication
public class JsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsServerApplication.class, args);
    }

}
