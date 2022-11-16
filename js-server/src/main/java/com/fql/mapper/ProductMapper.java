package com.fql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fql.entity.NavModel;
import com.fql.entity.ProductModel;
import com.fql.entity.StudentModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductMapper extends BaseMapper<ProductModel> {
    int add(ProductModel p);
    int addNav(NavModel n);
    int remove(int id);
    int removeNav(int id);
    int modify(ProductModel p);
    List<ProductModel> findAll(ProductModel p);
    List<NavModel> findNav();

}
