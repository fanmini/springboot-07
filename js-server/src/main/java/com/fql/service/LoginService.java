package com.fql.service;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;

/**
 * @author Qian
 */
public interface LoginService {
    ResultModel login(UserModel user);

    ResultModel loginOut();
}
