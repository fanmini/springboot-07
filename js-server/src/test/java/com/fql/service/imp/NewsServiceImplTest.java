package com.fql.service.imp;

import com.fql.entity.EsNewsEntity;
import com.fql.entity.NewsModel;
import com.fql.entity.ResultModel;
import com.fql.repository.es.NewsEsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NewsServiceImplTest {
    @Autowired
    private NewsServiceImpl service;

    @Test
    void save() {
        NewsModel model = new NewsModel();
        model.setContent("xxx 123 xxx");
        model.setId(123);
        service.save(model);
    }
    @Test
    void delete(){
        service.deleteById(1);
    }


    @Test
    void findAllByLike() {
        NewsModel newsModel = new NewsModel();
        newsModel.setContent("xxx");
        ResultModel all = service.findAllByLike(newsModel);
        Object data = all.getData();
        for (EsNewsEntity datum : (List<EsNewsEntity>) data) {
            System.out.println(datum.toString());
        }

    }
}