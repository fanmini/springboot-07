package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.OurTeamModel;
import com.fql.repository.OurTeamRepository;
import org.springframework.stereotype.Service;

@Service
public class OurTeamServiceImpl extends BaseServiceImpl<OurTeamModel,Integer, OurTeamRepository>  {
    public OurTeamServiceImpl(OurTeamRepository repository) {
        super(repository,RedisKeyPrefixEnum.OUR_TEAM_KEY.getKey());
    }
}
