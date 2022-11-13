package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.ProductModel;
import com.fql.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductModel,Integer, ProductRepository>  {
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }

    @Override
    void setPrefixKey() {
        super.prefixKey= RedisKeyPrefixEnum.PRODUCT_KEY.getKey();
    }
}
