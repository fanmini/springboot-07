package com.fql.service.imp;

import com.fql.common.JwtUtil;
import com.fql.common.RedisUtil;
import com.fql.entity.LoginUser;
import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Qian
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public ResultModel login(UserModel user) {
        // 封装前端账户密码到UsernamePasswordAuthenticationToken类中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //传递前端user authenticate:会调用我们自定义的UserDetailsImpl 并进行验证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户密码错误");
        }
        // authenticate.getPrincipal 获取自定义的UserDetailsImpl返回的数据LoginUserImpl类
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 使用userid生成token
        String id = loginUser.getUserModel().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        // authenticate 存储
        redisUtil.setCacheObject("login:"+id,loginUser);
        // 返回token
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResultModel.getResultModel("登录成功",map);
    }

    @Override
    public ResultModel loginOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String key = "login:"+userModel.getId();
        String msg = redisUtil.deleteObject(key)?"退出成功":"退出失败";


        return ResultModel.getResultModel(msg,userModel.getUserName());
    }
}
