package com.fql.controller;

import com.fql.entity.OurTeamModel;
import com.fql.entity.ResultModel;
import com.fql.service.imp.OurTeamServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


/**
 * @author Qian
 */
@RestController
@RequestMapping(value = "/back/ourTeam")
@Api(value = "api接口", tags = {"教练管理"})
@RolesAllowed({"system:admin:all", "system:back:ourteam"})
public class OurTeamController extends BaseController<OurTeamModel, Integer> {
    public OurTeamController(OurTeamServiceImpl service) {
        super(service);
    }


    @PreAuthorize("hasAnyAuthority('system:back:ourteam','system:admin:all')")
    @Override
    public ResultModel add(OurTeamModel ourTeamModel) {
        return super.add(ourTeamModel);
    }

    @PreAuthorize("hasAnyAuthority('system:back:ourteam','system:admin:all')")

    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:back:ourteam','system:admin:all')")

    @Override
    public ResultModel set(OurTeamModel ourTeamModel) {
        return super.set(ourTeamModel);
    }
}
