package com.stu.worktracer.dto;

import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.utils.RegexUtil;

public class RegisterRequest {
    private String username;
    private String pw;

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String validate() {
        if (username.length() > 16 || username.length() < 4) {
            return ErrCode.USERNAME_FORMAT_ERROR;
        }
        if (pw.length() > 16 || pw.length() < 4) {
            return ErrCode.PASSWORD_FORMAT_ERROR;
        }

        if (!RegexUtil.USERNAME_PTN.matcher(username).matches()) {
            return ErrCode.USERNAME_FORMAT_ERROR;
        }
        return null;
    }
}
