package com.fql.repository;

import com.fql.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Qian
 * 用户持久化
 */
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    /**
     *用户登录查询
     * @return
     */
    UserModel findByUserNameAndPassword(String userName, String password);

    /**
     * 查询用户是否存在
     * @param userName
     * @return
     */
    UserModel findByUserName(String userName);


}
