package com.fql.controller;

import com.fql.entity.NavModel;
import com.fql.service.imp.NavServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/back/nav")
@Api(value = "api接口",tags = {"标题管理"})

public class NavController extends BaseController<NavModel,Integer> {
    public NavController(NavServiceImpl service) {
        super(service);
    }
}
