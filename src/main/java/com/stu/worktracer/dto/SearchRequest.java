package com.stu.worktracer.dto;

public class SearchRequest {
    private String search;
    private int page;
    private int size;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean validate() {
        if (page < 0) {
            return false;
        }
        if (search.length() > 96) {
            search = search.substring(0, 96);
        }
        if (size > 96) {
            size = 96;
        }
        return true;
    }
}
