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

    // 实现了父类对redis的前缀的赋值 用于去对redis中应组的数据的操作
    @Override
    void setPrefixKey() {
        super.prefixKey = RedisKeyPrefixEnum.USER_KEY.getKey();
    }

    // 检查用户以及密码是否正确
    @Override
    public UserModel checkLogin(String userName, String password) {
        return repository.findByUserNameAndPassword(userName, password);
    }


    // 根据姓名查询用户是否存在
    @Override
    public UserModel findByName(String userName) {
        return repository.findByUserName(userName);
    }

}
