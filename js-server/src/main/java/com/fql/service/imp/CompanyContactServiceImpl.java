package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.CompanyContactModel;
import com.fql.entity.CompanyProfileModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.CompanyContactMapper;
import com.fql.mapper.CompanyProfileMapper;
import com.fql.repository.CompanyContactRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qian
 */
@Service
public class CompanyContactServiceImpl extends BaseServiceImpl<CompanyContactModel,Integer,CompanyContactRepository> {
    public CompanyContactServiceImpl(CompanyContactRepository repository) {
        super(repository,RedisKeyPrefixEnum.COMPANY_CONTACT_KEY.getKey());
    }
    @Resource
    private CompanyContactMapper mapper ;


    @Override
    public ResultModel findAllByLike(CompanyContactModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

}
