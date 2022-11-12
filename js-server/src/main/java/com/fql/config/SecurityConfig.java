package com.fql.config;

import com.fql.common.AccessDeniedHandlerImpl;
import com.fql.common.AuthenticationException;
import com.fql.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Qian
 * 安全框架配置
 * security
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 返回
     * BCryptPasswordEncoder
     * 密码加密器
     * 不在使用security框架的加密器：{noop}1234方式
     * @return
     */
    @Autowired
    JwtAuthenticationTokenFilter tokenFilter ;
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler ;
    @Autowired
    AuthenticationException authenticationException ;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 访问策略配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                // 不通过Session获取securityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口，允许匿名访问
                .antMatchers("/login","/code","/logOut").anonymous()
                // 除了外面的的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        // 添加filter到security过滤链中
        // 并且放在UsernamePasswordAuthenticationFilter过滤器前边执行通过字节码的方式判断
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 配置自定义 认证授权错误配置
        http.exceptionHandling().authenticationEntryPoint(authenticationException)
                .accessDeniedHandler(accessDeniedHandler);
        //开启跨域
        http.cors();
    }
}
