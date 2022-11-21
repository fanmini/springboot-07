package com.fql.repository.jpa;

import com.fql.entity.BankEsEntity;
import com.fql.entity.NewsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsModel,Integer> {

}
