package com.stu.worktracer.dto;

public class DetailCompany {
    private Long companyId;
    private String name;
    private String workshop;
    private Integer totalRate;
    private Long startWorkTimeWeek;
    private Long offWorkTimeWeek;
    private Long startWorkTimeMonth;
    private Long offWorkTimeMonth;
    private Integer workOvertime;
    private Integer welfare;

    public DetailCompany() {
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

    public Integer getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Integer totalRate) {
        this.totalRate = totalRate;
    }

    public Long getStartWorkTimeWeek() {
        return startWorkTimeWeek;
    }

    public void setStartWorkTimeWeek(Long startWorkTimeWeek) {
        this.startWorkTimeWeek = startWorkTimeWeek;
    }

    public Long getOffWorkTimeWeek() {
        return offWorkTimeWeek;
    }

    public void setOffWorkTimeWeek(Long offWorkTimeWeek) {
        this.offWorkTimeWeek = offWorkTimeWeek;
    }

    public Long getStartWorkTimeMonth() {
        return startWorkTimeMonth;
    }

    public void setStartWorkTimeMonth(Long startWorkTimeMonth) {
        this.startWorkTimeMonth = startWorkTimeMonth;
    }

    public Long getOffWorkTimeMonth() {
        return offWorkTimeMonth;
    }

    public void setOffWorkTimeMonth(Long offWorkTimeMonth) {
        this.offWorkTimeMonth = offWorkTimeMonth;
    }

    public Integer getWorkOvertime() {
        return workOvertime;
    }

    public void setWorkOvertime(Integer workOvertime) {
        this.workOvertime = workOvertime;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }
}
