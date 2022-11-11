package com.fql.service.imp;

import com.fql.entity.NavModel;
import com.fql.repository.NavRepository;
import org.springframework.stereotype.Service;

@Service
public class NavServiceImpl extends BaseServiceImpl<NavModel,Integer, NavRepository>  {
    public NavServiceImpl(NavRepository repository) {
        super(repository);
    }
}
