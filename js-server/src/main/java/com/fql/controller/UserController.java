package com.fql.controller;

import com.fql.entity.UserModel;
import com.fql.service.imp.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qian
 */
@RestController()
@RequestMapping("/back/user")
@Api(value = "api接口",tags = {"用户管理"})
public class UserController extends BaseController<UserModel,Integer> {
    public UserController(UserServiceImpl service) {
        super(service);
    }

}
