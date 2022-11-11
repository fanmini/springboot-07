package com.fql.service.imp;

import com.fql.entity.CompanyContactModel;
import com.fql.repository.CompanyContactRepository;
import org.springframework.stereotype.Service;

/**
 * @author Qian
 */
@Service
public class CompanyContactServiceImpl extends BaseServiceImpl<CompanyContactModel,Integer,CompanyContactRepository> {
    public CompanyContactServiceImpl(CompanyContactRepository repository) {
        super(repository);
    }
}
