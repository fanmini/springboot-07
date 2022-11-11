package com.fql.controller;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.imp.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qian
 */
@RestController
public class LoginController {
    /**
     *
     * @param user
     * @return
     */
    @Autowired
    LoginServiceImpl loginUser;
    @PostMapping("/back/login")
    public ResultModel login(@RequestBody UserModel user){
        return loginUser.login(user);
    }
    @GetMapping(value = "/back/loginOut")
    public ResultModel loginOut(){
        return loginUser.loginOut();
    }


}
