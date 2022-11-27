package com.fql.service.imp;

import com.fql.common.Rediskey;
import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.mapper.UserMapper;
import com.fql.repository.jpa.UserRepository;
import com.fql.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        super(repository, Rediskey.USER_KEY.getKey());
    }
    @Resource
    private UserMapper mapper ;
    @Resource
    private PasswordEncoder pass ;

    /**
     * 模糊查询
     * @param userModel
     * @return
     */
    @Override
    public ResultModel findAllByLike(UserModel userModel){
        PageHelper.startPage(userModel.getPage(),userModel.getLimit());
        List<UserModel> all = mapper.findAll(userModel);
        PageInfo<UserModel> result = new PageInfo<>(all);
        Integer count = Math.toIntExact(result.getTotal());
        return ResultModel.getResultModel(count,all);
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
