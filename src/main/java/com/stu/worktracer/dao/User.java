package com.stu.worktracer.dao;

public class User {
    private Long uid;
    private String username;
    private String pw;
    private Integer surveyed;

    public User() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public Integer getSurveyed() {
        return surveyed;
    }

    public void setSurveyed(Integer surveyed) {
        this.surveyed = surveyed;
    }
}
