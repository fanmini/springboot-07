package com.fql.controller;

import com.fql.entity.CustomerModel;
import com.fql.service.imp.CustomerServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/back/customer")
@Api(value = "api接口",tags = {"顾客管理"})

public class CustomerController extends BaseController<CustomerModel,Integer> {
    public CustomerController(CustomerServiceImpl service) {
        super(service);
    }
}
