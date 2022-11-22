package com.fql.repository.es;

import com.fql.entity.BankEsEntity;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;


@SpringBootTest
class BankEsRepositoryTest {
    @Autowired
    private BankEsRepository bankRepos ;
    @Resource
    private ElasticsearchClient client ;
    String index ="test";
    @Test
    public void test(){
        BankEsEntity bank = new BankEsEntity();
        bank.setId(11);
        bank.setEmail("xxx@example.com");
        BankEsEntity save = bankRepos.save(bank);

    }

}