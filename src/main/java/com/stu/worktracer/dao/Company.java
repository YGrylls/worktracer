package com.stu.worktracer.dao;


public class Company {
    private Long companyId;
    private String name;
    private String workshop;
    private Integer rate;
    private Integer overTimePercent;

    public Company(){

    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public Integer getOverTimePercent() {
        return overTimePercent;
    }

    public void setOverTimePercent(Integer overTimePercent) {
        this.overTimePercent = overTimePercent;
    }
}
