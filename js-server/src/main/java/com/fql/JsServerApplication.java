package com.fql;

import com.fql.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Qian
 */
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@EnableTransactionManagement
public class JsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsServerApplication.class, args);
    }

}
