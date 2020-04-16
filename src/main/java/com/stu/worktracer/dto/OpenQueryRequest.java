package com.stu.worktracer.dto;

public class OpenQueryRequest {
    private Long companyId;
    private Long from;
    private Long to;
    private Integer page;
    private Integer size;

    public OpenQueryRequest() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean validate() {
        if (companyId == null || from == null || to == null || page == null || size == null) {
            return false;
        }
        if (from >= to) {
            return false;
        }
        if (page < 0) {
            return false;
        }
        if (size > 10000) {
            size = 10000;
        }
        return true;
    }
}
