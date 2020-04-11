package com.stu.worktracer.dao;

import java.util.Date;


//a temperate entity
//everyday this table will be cleaned, data will be transfer to es
public class CheckInOut {
    private long uid;
    private long companyId;
    private int percentFix;
    private Date checkIn;
    private Date checkOut;

    public CheckInOut() {
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public int getPercentFix() {
        return percentFix;
    }

    public void setPercentFix(int percentFix) {
        this.percentFix = percentFix;
    }
}
