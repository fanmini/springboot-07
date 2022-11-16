package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.CompanyProfileModel;
import com.fql.entity.CustomerModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CompanyProfileMapper extends BaseMapper<CompanyProfileModel> {
    List<CompanyProfileModel> findAll(CompanyProfileModel cp);

}
