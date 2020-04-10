package com.stu.worktracer.dao;


public class Company {
    private long companyId;
    private String name;
    private String workshop;
    private int rate;
    private int overTimePercent;

    public Company(){

    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getOverTimePercent() {
        return overTimePercent;
    }

    public void setOverTimePercent(int overTimePercent) {
        this.overTimePercent = overTimePercent;
    }
}
