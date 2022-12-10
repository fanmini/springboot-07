package com.fql.service.imp;

import com.fql.util.RedisKey;
import com.fql.entity.ProductModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.ProductMapper;
import com.fql.repository.jpa.ProductRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductModel,Integer, ProductRepository>  {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository, RedisKey.PRODUCT_KEY.getKey());
    }
    @Resource
    private ProductMapper mapper ;


    @Override
    public ResultModel findAllByLike(ProductModel entity) {
        PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<ProductModel> all = mapper.findAll(entity);
        PageInfo<ProductModel> result = new PageInfo<>(all);
        Integer total = Math.toIntExact(result.getTotal());
        return ResultModel.getResultModel(total,all);
    }

}
