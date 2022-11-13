package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.NewsModel;
import com.fql.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsModel,Integer, NewsRepository> {
    public NewsServiceImpl(NewsRepository repository) {
        super(repository);
    }
    @Override
    void setPrefixKey() {
        super.prefixKey= RedisKeyPrefixEnum.NEWS_KEY.getKey();
    }
}
