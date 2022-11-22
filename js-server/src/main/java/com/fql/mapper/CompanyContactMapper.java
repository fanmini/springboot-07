package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fql.entity.CompanyContactModel;
import com.fql.entity.CompanyProfileModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @author Qian
 */
@Mapper
public interface CompanyContactMapper extends BaseMapper<CompanyContactModel> {
    /**
     * 公司联系模块 模糊查询
     * @param cp
     * @return
     */
    List<CompanyContactModel> findAll(CompanyContactModel cp);

}
