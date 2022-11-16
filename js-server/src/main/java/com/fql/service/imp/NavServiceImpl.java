package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.NavModel;
import com.fql.entity.OurTeamModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.OurTeamMapper;
import com.fql.repository.NavRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NavServiceImpl extends BaseServiceImpl<NavModel,Integer, NavRepository>  {
    public NavServiceImpl(NavRepository repository) {
        super(repository,RedisKeyPrefixEnum.NAV_KEY.getKey());
    }

    @Override
    public ResultModel findAllByLike(NavModel entity) {
        return null ;
    }

    public ResultModel findAllByType(Integer type){
        List<NavModel> all = repository.findAllByType(type);
        return ResultModel.getResultModel(all);

    }

}



