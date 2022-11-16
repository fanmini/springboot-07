package com.fql.controller;

import com.fql.entity.OurTeamModel;
import com.fql.service.imp.OurTeamServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Qian
 */
@RestController
@RequestMapping(value = "/back/ourTeam")
@Api(value = "api接口",tags = {"教练管理"})
public class OurTeamController extends BaseController<OurTeamModel,Integer> {
    public OurTeamController(OurTeamServiceImpl service) {
        super(service);
    }
}
