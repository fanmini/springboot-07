package com.fql.service.imp;

import com.fql.util.RedisKey;
import com.fql.entity.OurTeamModel;
import com.fql.entity.ResultModel;
import com.fql.mapper.OurTeamMapper;
import com.fql.repository.jpa.OurTeamRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OurTeamServiceImpl extends BaseServiceImpl<OurTeamModel,Integer, OurTeamRepository>  {
    public OurTeamServiceImpl(OurTeamRepository repository) {
        super(repository, RedisKey.OUR_TEAM_KEY.getKey());
    }
    @Resource
    private OurTeamMapper mapper ;


    @Override
    public ResultModel findAllByLike(OurTeamModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }
}
