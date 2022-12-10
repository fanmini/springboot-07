package com.fql.controller;

import com.fql.entity.NewsModel;
import com.fql.entity.ResultModel;
import com.fql.service.imp.NewsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController()
@RequestMapping("/back/news")
@Api(value = "api接口", tags = {"新闻管理"})

public class NewsController extends BaseController<NewsModel, Integer> {
    public NewsController(NewsServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:news')")
    @Override
    public ResultModel add(NewsModel newsModel) {
        return super.add(newsModel);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:news')")
    @Override
    public ResultModel delById(Integer integer) {
        return super.delById(integer);
    }

    @PreAuthorize("hasAnyAuthority('system:admin:all','system:back:news')")
    @Override
    public ResultModel set(NewsModel newsModel) {
        return super.set(newsModel);
    }
}
