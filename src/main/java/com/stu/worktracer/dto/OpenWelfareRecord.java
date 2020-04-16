package com.stu.worktracer.dto;

public class OpenWelfareRecord {
    private Long companyId;
    private String companyName;
    private Integer welfare;
    private Long surveyTime;

    public OpenWelfareRecord() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }

    public Long getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Long surveyTime) {
        this.surveyTime = surveyTime;
    }
}
