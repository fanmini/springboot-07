package com.fql.common;

/**
 * @author Qian
 */
public class ErrorMsgCode {
    private int code;
    private String msg;

    public ErrorMsgCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorMsgCode() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
