package com.fql.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author Qian
 */
@Configuration
public class DruidConfig {
    /**
    * druid
    * */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druid(){
        return new DruidDataSource();
    }

}