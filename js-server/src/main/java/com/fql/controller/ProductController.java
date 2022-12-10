package com.fql.controller;

import com.fql.entity.ProductModel;
import com.fql.entity.ResultModel;
import com.fql.service.imp.ProductServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/back/product")
@Api(value = "api接口", tags = {"产品管理"})
public class ProductController extends BaseController<ProductModel, Integer> {
    public ProductController(ProductServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasAnyAuthority('system:back:product','system:admin:all')")
    @Override
    public ResultModel add(ProductModel productModel) {
        return super.add(productModel);
    }

    @PreAuthorize("hasAnyAuthority('system:back:product','system:admin:all')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:back:product','system:admin:all')")
    @Override
    public ResultModel set(ProductModel productModel) {
        return super.set(productModel);
    }
}
