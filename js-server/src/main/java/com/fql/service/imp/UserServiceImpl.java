package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.repository.UserRepository;
import com.fql.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qian
 * 用户业务逻辑的具体是实现
 * 通过基类的增删改查 以及用户的拓展业务
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel,Integer,UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
    // 设置 user key 用于redis 缓存取值
    private String key = RedisKeyPrefixEnum.USER_KEY.getKey();
    @Override
    public UserModel checkLogin(String userName, String password) {
        return repository.findByUserNameAndPassword(userName, password);
    }

    @Override
    public UserModel findByName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public ResultModel findAll() {
        // 通过工具类方法获取redis数据
        List<Object> userModels = redisUtil.getCacheList(key);
        // 判断是否存在缓存
        if(userModels.size()> 0){
            return ResultModel.getResultModel(userModels);
        }else {
            // 不存在调用父类 并且存储
            ResultModel all = super.findAll();
            List<UserModel> data = (List<UserModel>) all.getData();
            redisUtil.setCacheList(key,data);
            return all;
        }
    }

    @Override
    public <S extends UserModel> ResultModel save(S entity) {
        ResultModel save = super.save(entity);
        redisUtil.setCacheObject(key,save.getData());
        return save;
    }
}
