package com.fql.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

/**
 * @author 樊乾浪
 * @date 2022/11/25 10:07
 * @company xxx
 */
@Component
public class Son implements FatherA,FatherB{
    @Override
    public void test() {
        System.out.println("xxx");
    }


}
