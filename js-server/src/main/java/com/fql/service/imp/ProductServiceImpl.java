package com.fql.service.imp;

import com.fql.common.Rediskey;
import com.fql.entity.ProductModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.ProductMapper;
import com.fql.repository.jpa.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductModel,Integer, ProductRepository>  {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository, Rediskey.PRODUCT_KEY.getKey());
    }
    @Resource
    private ProductMapper mapper ;


    @Override
    public ResultModel findAllByLike(ProductModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

}
