package com.fql.repository.es;

import com.fql.entity.NewsEsModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NewsRepositoryTest {
    @Autowired
    NewsEsRepository repository ;
    @Test
    public void test(){
        NewsEsModel newsModel = new NewsEsModel();
        newsModel.setId(111);
        newsModel.setContent("xxxx");
        newsModel.setTitle("test");
        repository.save(newsModel);
    }

}