package com.fql.service.imp;

import com.fql.common.ErrorMsgCodeEnum;
import com.fql.entity.VerifyCode;
import com.fql.util.Base64Utils;
import com.fql.util.JwtUtil;
import com.fql.util.RedisUtil;
import com.fql.entity.UserDetailsEntity;
import com.fql.entity.ResultModel;
import com.fql.entity.UserModel;
import com.fql.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Qian
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public ResultModel getCode(HttpServletResponse resp){
        // 创建验证码并存储验证码
        String codeKey = UUID.randomUUID().toString();
        String numberValue = Base64Utils.getNumber(5);
        redisUtil.setCacheObject(codeKey,numberValue,2, TimeUnit.MINUTES);
        // 获取图片验证码
        String code = null;
        try {
            code = Base64Utils.drawImage(resp, numberValue);
        } catch (IOException e) {
            log.error("验证码生成失败");
        }
        VerifyCode v = new VerifyCode(codeKey,code);
        //在响应体中，返回有验证码本及保存验证码的uuid
        return ResultModel.getResultModel(v);
    }

    @Override
    public ResultModel login(UserModel user) {
        // 封装前端账户密码到UsernamePasswordAuthenticationToken类中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //传递前端user authenticate:会调用我们自定义的UserDetailsImpl 并进行验证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
        // 用户密码账号错误
            throw new RuntimeException(ErrorMsgCodeEnum.ERROR_USERNAME_PWD.toString());
        }
        // authenticate.getPrincipal 获取自定义的UserDetailsImpl返回的数据LoginUserImpl类
        UserDetailsEntity userDetailsEntity = (UserDetailsEntity) authenticate.getPrincipal();
        // 使用userid生成token
        String id = userDetailsEntity.getUserModel().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        // authenticate 存储
        redisUtil.setCacheObject("login:"+id, userDetailsEntity,30,TimeUnit.MINUTES);
        // 返回token
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",user.getUserName());
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
