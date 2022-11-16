package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.OurTeamModel;
import com.fql.entity.ProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OurTeamMapper extends BaseMapper<OurTeamModel> {
    List<OurTeamModel> findAll(OurTeamModel ot);

}
