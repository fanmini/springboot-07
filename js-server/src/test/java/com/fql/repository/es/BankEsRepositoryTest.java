package com.fql.repository.es;

import com.fql.entity.BankEsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BankEsRepositoryTest {
    @Autowired
    private BankEsRepository bankRepos ;
    @Test
    public void test(){
        BankEsEntity bank = new BankEsEntity();
        bank.setId(11);
        bank.setEmail("xxx@example.com");
        bankRepos.save(bank);
    }

}