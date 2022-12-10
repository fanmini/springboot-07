package com.fql.controller;

import com.fql.service.imp.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @author 樊乾浪
 * @date 2022/12/9 9:05
 * @company xxx
 */
@SpringBootTest
class UserControllerTest {

    @Resource
    UserServiceImpl user ;

    @Test
    void addRole() {

    }
}