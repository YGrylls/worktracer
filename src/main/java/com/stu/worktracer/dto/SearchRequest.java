package com.stu.worktracer.dto;

public class SearchRequest {
    private String search;
    private int page;

    public SearchRequest() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
