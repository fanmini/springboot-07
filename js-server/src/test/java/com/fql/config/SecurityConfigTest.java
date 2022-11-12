package com.fql.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
class SecurityConfigTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test(){
        String qwer1234 = passwordEncoder.encode("qwer1234");
        System.out.println(qwer1234);
        String a ="$2a$10$huDQkLPMWxyWIi1JGQR80.36Y/Fm6AiWlIOGWI5X0.VzcFD3z.MMu";
    }


}