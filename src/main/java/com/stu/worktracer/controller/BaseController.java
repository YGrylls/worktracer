package com.stu.worktracer.controller;

public class BaseController {
    private ThreadLocal<Long> authUid = new ThreadLocal<>();

    public void setUid(Long uid) {
        authUid.remove();
        authUid.set(uid);
    }

    public void clearUid() {
        authUid.remove();
    }

    protected Long getUid() {
        return authUid.get();
    }
}
