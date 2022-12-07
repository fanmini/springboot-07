package com.fql.mytest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
        int i = this.hashCode();
        System.out.println("xxx");
    }
    @Test
    void testHash(){
        Son son = new Son();
        System.out.println(this.equals(son));
        String a = "xx";
        String b = "xx";
        System.out.println(a.equals(b));
    }


}
