package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<UserModel> {
    /**
     * 模糊查询用户信息
     * @param userModel
     * @return
     */
    List<UserModel> findAll(UserModel userModel);

}
