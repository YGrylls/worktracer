package com.stu.worktracer.service;

import com.stu.worktracer.dto.DetailCompany;
import com.stu.worktracer.dto.SimpleCompany;
import com.stu.worktracer.error.KnownException;

import java.util.List;

public class CompanyService implements CompanyServiceInterface {
    @Override
    public List<SimpleCompany> getCompanyList(String search, int page, int size) throws KnownException {
        return null;
    }

    @Override
    public DetailCompany getCompanyInfo(Long companyId) throws KnownException {
        return null;
    }

    @Override
    public void submitNewCom(String companyName, String workshop) throws KnownException {

    }
}
