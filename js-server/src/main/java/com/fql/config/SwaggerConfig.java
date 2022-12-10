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
public class SwaggerConfig {
    /**配置参数
     * Swagger config file
     * */
    @Bean
    public Docket docket(){
       return  new Docket(DocumentationType.OAS_30).apiInfo(getInfo());
    }
    private ApiInfo getInfo() {
        return  new ApiInfo(
                "健身项目swagger",
                "项目前后端分离，这是后台的接口管理所有的接口都是返回的json对象，分别是，" +
                        "\ncode:0 成功  " +
                        "\nmsg：消息提示 " +
                        "\ncount:返回条数" +
                        "\nObject:返回数据",
                "v3",
                "暂无服务条款",
                new Contact("fanmini","http://localhost","m14785769048@163.com"),
                "license",
                "licenseUrl",
                new ArrayList<>()
        );
    }

}