package com.stu.worktracer.error;

public class KnownException extends Exception {
    private String errCode;
    private String msg;

    public KnownException() {
        super();
    }

    public KnownException(Throwable cause) {
        super(cause);
    }

    public KnownException(String code, String msg, Throwable cause) {
        super(cause);
        this.errCode = code;
        this.msg = msg;
    }

    public KnownException(String code, Throwable cause) {
        super(cause);
        this.errCode = code;
        this.msg = code;
    }

    public KnownException(String code, String msg) {
        super();
        this.errCode = code;
        this.msg = msg;
    }

    public KnownException(String code) {
        super();
        this.errCode = code;
        this.msg = code;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
