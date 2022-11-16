package com.fql.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Qian
 */
public class WebUtils
{
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
//            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            // 此处 setHeader、addHeader 方法都可用。但 addHeader时写多个会报错：“...,but only one is allowed”
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,token,codeKey,Content-Type,Token, Content-Type");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.getWriter().print(string);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}