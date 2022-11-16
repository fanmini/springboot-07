package com.fql.controller;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.imp.UserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qian
 */
@RestController()
@RequestMapping("/back/user")
@Api(value = "api接口",tags = {"用户管理"})
public class UserController extends BaseController<UserModel,Integer> {
    @Autowired
    UserServiceImpl  u ;
    public UserController(UserServiceImpl service) {
        super(service);
    }

    @PutMapping("/pwd")
    public ResultModel setPwd(@RequestBody UserModel user){
        return u.setPwd(user);
    }


}
