package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.CompanyContactModel;
import com.fql.entity.CompanyProfileModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CompanyContactMapper extends BaseMapper<CompanyContactModel> {
    List<CompanyContactModel> findAll(CompanyContactModel cp);


}
