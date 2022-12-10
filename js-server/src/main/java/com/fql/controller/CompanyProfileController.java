package com.fql.controller;

import com.fql.entity.CompanyProfileModel;
import com.fql.entity.ResultModel;
import com.fql.service.imp.CompanyProfileServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author Qian
 */
@RestController
@RequestMapping(value = "/back/companyProfile")
@Api(value = "api接口", tags = {"公司详情管理"})

public class CompanyProfileController extends BaseController<CompanyProfileModel, Integer> {
    public CompanyProfileController(CompanyProfileServiceImpl service) {
        super(service);
    }


    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:system:back:company:profile')")
    @Override
    public ResultModel add(CompanyProfileModel companyProfileModel) {
        return super.add(companyProfileModel);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:system:back:company:profile')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:system:back:company:profile')")
    @Override
    public ResultModel set(CompanyProfileModel companyProfileModel) {
        return super.set(companyProfileModel);
    }
}
