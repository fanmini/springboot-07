package com.fql.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Qian
 */
@Slf4j
public class SerializeUtil {
    static {
        JSON=new ObjectMapper();
    }
    private static ObjectMapper JSON ;
    /**
     * 序列化
     */
   public static String bojToJson(Object o){
       try {
           String s = JSON.writeValueAsString(o);
           return s ;
       } catch (JsonProcessingException e) {
           e.printStackTrace();
           log.warn("序列化类：{} 失败",o.getClass().getName());
       }
        return "";

   }

    /**
     * 反序列化
     */
    public static  <T> T jsonToObj(Message json, Class<T> cls){
        try {
            byte[] body = json.getBody();
            return JSON.readValue(body,cls);
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("反序列化类：{} 失",cls.getName());
        }
        return null ;
    }
}
