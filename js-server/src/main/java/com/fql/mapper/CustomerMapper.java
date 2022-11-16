package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.CustomerModel;
import com.fql.entity.NewsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CustomerMapper extends BaseMapper<CustomerModel> {
    List<CustomerModel> findAll(CustomerModel c);
}
