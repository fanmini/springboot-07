package com.fql.common;

/**
 * @author Qian
 */

public enum ErrorMsgCodeEnum {
    // 返回前端错误枚举消息
    ERROR_USERNAME_PWD(new ErrorMsgCode(1000,"用户密码错误")),
    ERROR_VERIFY_CODE(new ErrorMsgCode(1001,"验证码异常,请刷新重试")),
    ERROR_LOGIN_NO(new ErrorMsgCode(1003,"请先登录")),

    ERROR_ADD(new ErrorMsgCode(2000,"添加失败,请检查添加内容是否合法")),
    ERROR_DEL(new ErrorMsgCode(2001,"删除失败")),
    ERROR_SET(new ErrorMsgCode(2002,"修改失败")),
    ERROR_FIND(new ErrorMsgCode(2003,"查询失败")),

    ERROR_AUTHENTICATION(new ErrorMsgCode(3001,"登录失败请重新登录")),
    ERROR_ACCESS_DENIED(new ErrorMsgCode(3002,"您的权限不足"));

    private ErrorMsgCode errorMsgCode;
    ErrorMsgCodeEnum(ErrorMsgCode e){
        this.errorMsgCode=e;
    }

    public ErrorMsgCode getE() {
        return errorMsgCode;
    }

}
