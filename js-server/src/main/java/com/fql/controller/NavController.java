package com.fql.controller;

import com.fql.entity.NavModel;
import com.fql.entity.ResultModel;
import com.fql.service.imp.NavServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

/**
 * @author Qian
 */
@RestController
@RequestMapping(value = "/back/nav")
@Api(value = "api接口", tags = {"标题管理"})

public class NavController extends BaseController<NavModel, Integer> {
    public NavController(NavServiceImpl service) {
        super(service);
    }

    @Resource
    private NavServiceImpl navService;

    @ApiOperation(value = "传入int类型1=新闻标题，2=产品标题，查询对应标题", httpMethod = "GET")
    @GetMapping("/typeAll/{type}")
    public ResultModel findAllByType(@PathVariable("type") Integer type) {
        return navService.findAllByType(type);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:nav')")
    @Override
    public ResultModel add(NavModel navModel) {
        return super.add(navModel);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:nav')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:nav')")
    @Override
    public ResultModel set(NavModel navModel) {
        return super.set(navModel);
    }
}
