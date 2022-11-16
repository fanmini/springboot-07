package com.fql.controller;

import com.fql.entity.CompanyContactModel;
import com.fql.service.imp.CompanyContactServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/back/contact")
@Api(value = "api接口",tags = {"公司联系管理"})

public class CompanyContactController extends BaseController<CompanyContactModel,Integer> {
    public CompanyContactController(CompanyContactServiceImpl service) {
        super(service);
    }
}
