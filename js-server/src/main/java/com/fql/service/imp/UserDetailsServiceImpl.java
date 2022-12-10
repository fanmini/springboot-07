package com.fql.service.imp;

import com.fql.entity.UserDetailsEntity;
import com.fql.entity.UserModel;
import com.fql.mapper.MenuMapper;
import com.fql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Qian
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private MenuMapper mapper ;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询数据
        UserModel user = userServiceImpl.findByName(userName);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        // TODO:查询用户的角色

        //  查询对应的用户权限
        List<String> menuList = null;
        try {
            menuList = mapper.findPermsByUserId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("权限查询错误");
        }
        return new UserDetailsEntity(user,menuList);

    }
}
