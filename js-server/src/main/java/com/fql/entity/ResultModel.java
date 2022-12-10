package com.fql.entity;

import com.fql.err.ErrorMsgCode;
import com.fql.err.ErrorMsgCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qian
 */
@Data
@NoArgsConstructor
public class ResultModel {
    // 返回的状态码
    private Integer code ;
    // 返回信息
    private String msg ;
    // 影响的条数
    private Integer count ;
    // 返回的数据
    private Object data ;

    public ResultModel(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
    public static ResultModel getResultModel(ErrorMsgCodeEnum emc) {
        ErrorMsgCode e = emc.getE();
        return new ResultModel(e.getCode(),e.getMsg(),0,null);
    }


    public static ResultModel getResultModel(int count, Object data) {
        return new ResultModel(0,"",count,data);
    }
    public static ResultModel getResultModel(int count) {
        return new ResultModel(0,"",count,null);
    }

    public static ResultModel getResultModel(String msg, Object data) {
        return new ResultModel(0,msg,1,data);
    }
    public static ResultModel getResultModel(Object data) {
        return new ResultModel(0,"",0,data);
    }


}
