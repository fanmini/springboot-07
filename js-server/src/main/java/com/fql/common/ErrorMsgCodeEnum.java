package com.fql.common;

/**
 * @author Qian
 */

public enum ErrorMsgCodeEnum {
    // 返回前端错误枚举消息
    ERROR_USERNAME_PWD(new ErrorMsgCode(1000,"用户密码错误")),
    ERROR_VERIFY_CODE(new ErrorMsgCode(1001,"验证码异常,请刷新重试")),
    ERROR_LOGIN_NO(new ErrorMsgCode(1002,"请先登录")),
    ERROR_LOGIN_AUTHORITY(new ErrorMsgCode(1003,"权限不足")),

    ERROR_ADD(new ErrorMsgCode(2000,"添加失败")),
    ERROR_DEL(new ErrorMsgCode(2001,"删除失败")),
    ERROR_SET(new ErrorMsgCode(2002,"修改失败")),
    ERROR_FIND(new ErrorMsgCode(2003,"查询失败"));



    private ErrorMsgCode errorMsgCode;
    ErrorMsgCodeEnum(ErrorMsgCode e){
        this.errorMsgCode=e;
    }

    public ErrorMsgCode getE() {
        return errorMsgCode;
    }

    @Override
    public String toString() {
        return errorMsgCode.getCode()+":"+ errorMsgCode.getMsg();
    }
}
