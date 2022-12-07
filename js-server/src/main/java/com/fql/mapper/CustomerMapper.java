package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.CustomerModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CustomerMapper extends BaseMapper<CustomerModel> {
    List<CustomerModel> findAll(CustomerModel c);

    /**
     * 设置顾客要要收到的消息条数
     * @param msgCount
     */
    int setMsgCount(String msgCount);

    /**
     * 获取顾客信息通过id
     * 返回的是邮箱以及msgcount数
     * @param id
     * @return
     */
    CustomerModel findMegCountById(Integer id);

    List<CustomerModel> findAllMegCountById(String id);

    int successMes(Integer id);
}
