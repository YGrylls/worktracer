package com.stu.worktracer.service;

import com.stu.worktracer.dto.DetailCompany;
import com.stu.worktracer.dto.SimpleCompany;
import com.stu.worktracer.error.KnownException;

import java.util.List;

public interface CompanyServiceInterface {
    List<SimpleCompany> getCompanyList(String search, int page, int size) throws KnownException;

    DetailCompany getCompanyInfo(Long companyId) throws KnownException;

    void submitNewCom(String companyName, String workshop) throws KnownException;

}
