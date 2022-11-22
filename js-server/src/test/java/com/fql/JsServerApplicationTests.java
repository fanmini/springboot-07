package com.fql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsServerApplicationTests {
    @Value("${user.age}")
     Integer age ;
    @Value("${user.name}")
     String name ;
    @Value("${user.address[0]}")
    String address ;

    @Test
    void contextLoads() {
        System.out.println(age);
        System.out.println(name);
        System.out.println(address);
    }

}
