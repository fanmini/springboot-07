package com.fql.filter;

import com.fql.common.ErrorMsgCodeEnum;
import com.fql.util.JwtUtil;
import com.fql.util.RedisUtil;
import com.fql.entity.UserDetailsEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Qian
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private RedisUtil red ;
    JwtAuthenticationTokenFilter(RedisUtil util){
        this.red=util;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 登录拦截解析校验授权
        logCheck(httpServletRequest, httpServletResponse, filterChain);
    }

    private void logCheck(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        // 获取token
        String token = req.getHeader("token");
        // 空判断
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(req, resp);
            return;
        }
        // 解析
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("非法的token尝试登录后台管理系统");
            throw new RuntimeException(ErrorMsgCodeEnum.ERROR_LOGIN_NO.toString());
        }
        // 从redis中同userid获取用户信息ASDFG
        String redisKey = "login:"+userId;
        UserDetailsEntity userLogin = red.getCacheObject(redisKey);
        if(Objects.isNull(userLogin)){
            throw new RuntimeException(ErrorMsgCodeEnum.ERROR_LOGIN_NO.toString());
        }
        // 存入SecurityContextHolder
        //  获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin, null, userLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(req, resp);
    }
}
