package com.fql.service.imp;

import com.fql.common.Rediskey;
import com.fql.entity.NewsModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.NewsMapper;
import com.fql.repository.NewsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsModel,Integer, NewsRepository> {
    public NewsServiceImpl(NewsRepository repository) {
        super(repository, Rediskey.NEWS_KEY.getKey());
    }

    @Resource
    private NewsMapper mapper ;


    @Override
    public ResultModel findAllByLike(NewsModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }
}