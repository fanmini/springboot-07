package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.NavModel;
import com.fql.entity.ProductModel;
import com.fql.entity.StudentModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper extends BaseMapper<ProductModel> {
    List<ProductModel> findAll(ProductModel p);
}
