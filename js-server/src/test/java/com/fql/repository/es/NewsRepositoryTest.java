package com.fql.repository.es;

import com.fql.entity.EsNewsEntity;
import com.fql.entity.NewsModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NewsRepositoryTest {
    @Autowired
    NewsEsRepository repository ;
    @Test
    public void test(){
        EsNewsEntity newsModel = new EsNewsEntity();
        newsModel.setIndex(1);
        newsModel.setContent("xxxx");
        newsModel.setTitle("test");
        EsNewsEntity save = repository.save(newsModel);
        System.out.println(save.toString());
    }
    @Test
    public void testFind(){
        Iterable<EsNewsEntity> all = repository.findAll();
        for (EsNewsEntity esNewsEntity : all) {
            System.out.println(esNewsEntity.toString());
        }
    }

    @Test
    public void testDelete(){
        EsNewsEntity esNewsEntity = new EsNewsEntity();
        esNewsEntity.setIndex(1);
        repository.delete(esNewsEntity);

    }
}