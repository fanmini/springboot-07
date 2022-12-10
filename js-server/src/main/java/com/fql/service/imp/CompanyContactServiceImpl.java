package com.fql.service.imp;

import com.fql.util.RedisKey;
import com.fql.entity.CompanyContactModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.CompanyContactMapper;
import com.fql.repository.jpa.CompanyContactRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qian
 */
@Service
public class CompanyContactServiceImpl extends BaseServiceImpl<CompanyContactModel,Integer,CompanyContactRepository> {
    public CompanyContactServiceImpl(CompanyContactRepository repository) {
        super(repository, RedisKey.COMPANY_CONTACT_KEY.getKey());
    }
    @Resource
    private CompanyContactMapper mapper ;


    @Override
    public ResultModel findAllByLike(CompanyContactModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

}
