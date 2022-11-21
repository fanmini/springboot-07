package com.fql.repository.es;

import com.fql.entity.NewsEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Qian
 */
@Repository
public interface NewsEsRepository extends ElasticsearchRepository<NewsEsModel,Integer> {

}
