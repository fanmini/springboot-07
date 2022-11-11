package com.fql.service;

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
     */
    UserModel checkLogin(String userName,String password);

    /**
     *
     * @param userName
     * @return
     */
    UserModel findByName(String userName);
}
