package com.fql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.MenuModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Qian
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuModel> {
    /**
     * 通过传入一个用户的id 来获取对应的用户权限信息
     * @param id
     * @return
     */
    List<String> findPermsByUserId(Integer id);

}
