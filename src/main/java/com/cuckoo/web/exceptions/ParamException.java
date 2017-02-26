package com.cuckoo.web.exceptions;

/**
 * Created by tanmq on 2017/2/26.
 */
public class ParamException extends Exception {
    private int code;
    private String msg;

    public ParamException(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
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
