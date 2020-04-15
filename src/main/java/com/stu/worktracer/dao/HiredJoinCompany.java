package com.stu.worktracer.dao;

import java.util.Date;

public class HiredJoinCompany {
    private Long uid;
    private Long companyId;
    private Date modTime;
    private String name;
    private String workshop;
    private Integer rate;
    private Integer welfare;

    public HiredJoinCompany() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }
}
