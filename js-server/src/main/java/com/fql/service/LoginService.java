package com.fql.service;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Qian
 */
public interface LoginService {
    /**
     * 生成验证码
     * @return
     */
    ResultModel getCode(HttpServletResponse resp);


    ResultModel login(UserModel user);

    ResultModel loginOut();


}
