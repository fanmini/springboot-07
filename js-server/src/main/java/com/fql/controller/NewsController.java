package com.fql.controller;

import com.fql.entity.NewsModel;
import com.fql.service.imp.NewsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/back/news")
@Api(value = "api接口",tags = {"新闻管理"})

public class NewsController extends BaseController<NewsModel,Integer> {
    public NewsController(NewsServiceImpl service) {
        super(service);
    }


}
