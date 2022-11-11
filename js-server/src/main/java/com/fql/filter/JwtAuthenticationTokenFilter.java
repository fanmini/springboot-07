package com.fql.filter;

import com.fql.common.JwtUtil;
import com.fql.common.RedisUtil;
import com.fql.entity.LoginUser;
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
        // 不为空判断
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
            throw new RuntimeException("非法token");
        }
        // 从redis中同userid获取用户信
        String redisKey = "login:"+userId;
        LoginUser userLogin = red.getCacheObject(redisKey);
        if(Objects.isNull(userLogin)){
            throw new RuntimeException("用户未登录");
        }
        // 存入SecurityContextHolder
        //  获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin, null, userLogin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(req, resp);
    }
}
