package com.fql.service.imp;

import com.fql.entity.CustomerModel;
import com.fql.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel,Integer, CustomerRepository> {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
