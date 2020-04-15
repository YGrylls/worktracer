package com.stu.worktracer.dao;

import java.util.Date;


//a temperate entity
//everyday this table will be cleaned, data will be transfer to es
public class CheckInOut {
    private Long uid;
    private Long companyId;
    private Integer percentFix;
    private Date checkIn;
    private Date checkOut;

    //for join
    private String companyName;

    public CheckInOut() {
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

    public Integer getPercentFix() {
        return percentFix;
    }

    public void setPercentFix(Integer percentFix) {
        this.percentFix = percentFix;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
