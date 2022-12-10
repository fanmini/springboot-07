package com.fql.controller;

import com.fql.entity.CompanyContactModel;
import com.fql.entity.ResultModel;
import com.fql.service.imp.CompanyContactServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author Qian
 */
@RestController
@RequestMapping(value = "/back/contact")
@Api(value = "api接口", tags = {"公司联系管理"})
public class CompanyContactController extends BaseController<CompanyContactModel, Integer> {
    public CompanyContactController(CompanyContactServiceImpl service) {
        super(service);
    }


    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:company:contact')")
    @Override
    public ResultModel add(CompanyContactModel companyContactModel) {
        return super.add(companyContactModel);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:company:contact')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:company:contact')")
    @Override
    public ResultModel set(CompanyContactModel companyContactModel) {
        return super.set(companyContactModel);
    }
}
