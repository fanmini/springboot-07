package com.fql.repository;

import com.fql.entity.BankEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Qian
 */

@Repository
public interface BankEsRepository extends ElasticsearchRepository<BankEsEntity,Integer> {


}
