package com.fql.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fql.entity.ResultModel;
import com.fql.util.WebUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**捕获未认证授权 自定义异常，返回正常model
 * @author Qian
 */
@Component
public class AuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest res, HttpServletResponse resp, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        ResultModel resultModel = ResultModel.getResultModel(e.getMessage(),null);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(resultModel);
        WebUtils.renderString(resp,s);
    }
}
