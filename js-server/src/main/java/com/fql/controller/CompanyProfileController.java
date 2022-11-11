package com.fql.controller;

import com.fql.entity.CompanyProfileModel;
import com.fql.service.imp.CompanyProfileServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/back/companyProfile")
@Api(value = "api接口",tags = {"公司详情管理"})

public class CompanyProfileController extends BaseController<CompanyProfileModel,Integer> {
    public CompanyProfileController(CompanyProfileServiceImpl service) {
        super(service);
    }
}
