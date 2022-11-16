package com.fql.service.imp;

import com.fql.common.RedisKeyPrefixEnum;
import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.mapper.UserMapper;
import com.fql.repository.UserRepository;
import com.fql.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qian
 * 用户业务逻辑的具体是实现
 * 通过基类的增删改查 以及用户的拓展业务
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel,Integer,UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository,RedisKeyPrefixEnum.USER_KEY.getKey());
    }
    @Resource
    private UserMapper mapper ;
    @Resource
    private PasswordEncoder pass ;
    // 模糊查询
    @Override
    public ResultModel findAllByLike(UserModel userModel){
        List<UserModel> all = mapper.findAll(userModel);
        return ResultModel.getResultModel(all);
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

    @Override
    public ResultModel setPwd(UserModel user) {
        if (user.getPassword()!=null && user.getPassword()!=""){
            user.setPassword(pass.encode(user.getPassword()));
        }
        return super.save(user);
    }


}
