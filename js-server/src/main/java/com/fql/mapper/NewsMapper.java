package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.NavModel;
import com.fql.entity.NewsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface NewsMapper extends BaseMapper<NewsModel> {
    List<NewsModel> findAll(NewsModel n);

}
