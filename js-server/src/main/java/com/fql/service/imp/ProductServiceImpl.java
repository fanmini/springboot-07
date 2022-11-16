package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.ProductModel;
import com.fql.entity.ResultModel;
import com.fql.entity.StudentModel;
import com.fql.mapper.ProductMapper;
import com.fql.mapper.StudentMapper;
import com.fql.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductModel,Integer, ProductRepository>  {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository,RedisKeyPrefixEnum.PRODUCT_KEY.getKey());
    }
    @Resource
    private ProductMapper mapper ;


    @Override
    public ResultModel findAllByLike(ProductModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

}
