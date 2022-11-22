package com.fql.repository.es;

import com.fql.entity.EsNewsEntity;
import com.fql.entity.NewsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Qian
 */
@Repository
public interface NewsEsRepository extends ElasticsearchRepository<EsNewsEntity,Integer> {

}
