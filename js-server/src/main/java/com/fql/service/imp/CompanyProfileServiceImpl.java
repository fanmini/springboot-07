package com.fql.service.imp;

import com.fql.common.Rediskey;
import com.fql.entity.CompanyProfileModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.CompanyProfileMapper;
import com.fql.repository.CompanyProfileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qian
 */
@Service
public class CompanyProfileServiceImpl extends BaseServiceImpl<CompanyProfileModel,Integer, CompanyProfileRepository> {
    public CompanyProfileServiceImpl(CompanyProfileRepository repository) {
        super(repository, Rediskey.COMPANY_PROFILE_KEY.getKey());
    }
    @Resource
    private CompanyProfileMapper mapper ;


    @Override
    public ResultModel findAllByLike(CompanyProfileModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

}