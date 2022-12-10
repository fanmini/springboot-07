package com.fql.controller;

import com.fql.entity.CustomerModel;
import com.fql.entity.Mail;
import com.fql.entity.ResultModel;
import com.fql.service.imp.CustomerServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author Qian
 */
@RestController
@RequestMapping(value = "/back/customer")
@Api(value = "api接口", tags = {"顾客管理"})

public class CustomerController extends BaseController<CustomerModel, Integer> {
    public CustomerController(CustomerServiceImpl service) {
        super(service);
    }

    private CustomerServiceImpl service = (CustomerServiceImpl) super.service;

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:customer')")
    @PostMapping("/customerPush")
    public ResultModel customerPush(@RequestBody Mail mail) {
        return service.messagePush(mail);
    }


    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:customer')")
    @Override
    public ResultModel add(CustomerModel customerModel) {
        return super.add(customerModel);
    }


    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:customer')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }


    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:customer')")
    @Override
    public ResultModel set(CustomerModel customerModel) {
        return super.set(customerModel);
    }
}
