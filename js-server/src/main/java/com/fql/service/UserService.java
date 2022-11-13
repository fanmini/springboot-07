package com.fql.service;

import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;

/**
 * @author Qian
 * user业务拓展
 *  用户登录
 *
 */
public interface UserService {
    /**
     *
     * @param userName
     * @param password
     * @return
     * 检查登录
     */
    UserModel checkLogin(String userName,String password);

    /**
     *
     * @param userName
     * @return
     * 用于查询指定名称的用户信息
     */
    UserModel findByName(String userName);



}
