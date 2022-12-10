package com.fql.err;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fql.entity.ResultModel;
import com.fql.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 捕获权限不住，并自定义 异常后返回正常的ResultModel
 * @author Qian
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException {
        ResultModel resultModel = new ResultModel(1003,e.getMessage(),0,null);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(resultModel);
        WebUtils.renderString(resp,s);
    }
}
