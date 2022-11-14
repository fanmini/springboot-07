package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.CompanyProfileModel;
import com.fql.repository.CompanyProfileRepository;
import org.springframework.stereotype.Service;

/**
 * @author Qian
 */
@Service
public class CompanyProfileServiceImpl extends BaseServiceImpl<CompanyProfileModel,Integer, CompanyProfileRepository> {
    public CompanyProfileServiceImpl(CompanyProfileRepository repository) {
        super(repository,RedisKeyPrefixEnum.COMPANY_PROFILE_KEY.getKey());
    }

}