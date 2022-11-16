package com.fql.controller;

import com.fql.entity.ProductModel;
import com.fql.service.imp.ProductServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/back/product")
@Api(value = "api接口",tags = {"产品管理"})

public class ProductController extends BaseController<ProductModel,Integer> {
    public ProductController(ProductServiceImpl service) {
        super(service);
    }




}
