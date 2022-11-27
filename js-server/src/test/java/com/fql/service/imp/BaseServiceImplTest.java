package com.fql.service.imp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 樊乾浪
 * @date 2022/11/24 22:19
 * @company xxx
 */
class BaseServiceImplTest {
    @Autowired
    private NavServiceImpl nav ;

    @Test
    void findAll() {
        PageRequest of = PageRequest.of(1, 3, Sort.Direction.DESC);
        nav.findAll();



    }
}