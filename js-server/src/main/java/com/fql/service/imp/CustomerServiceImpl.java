package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.CustomerModel;
import com.fql.entity.OurTeamModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.CustomerMapper;
import com.fql.mapper.OurTeamMapper;
import com.fql.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel,Integer, CustomerRepository> {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository, RedisKeyPrefixEnum.CUSTOMER_KEY.getKey());
    }
    @Resource
    private CustomerMapper mapper ;


    @Override
    public ResultModel findAllByLike(CustomerModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }
}
