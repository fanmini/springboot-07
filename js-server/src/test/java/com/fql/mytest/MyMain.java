package com.fql.mytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 樊乾浪
 * @date 2022/11/25 14:22
 * @company xxx
 */
public class MyMain {
    @Autowired
    Son s ;
    @Test
    void test(){
        s.test();
    }

}
