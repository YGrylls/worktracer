package com.stu.worktracer.service;

import com.stu.worktracer.dto.OpenCheckRecord;
import com.stu.worktracer.dto.OpenCompany;
import com.stu.worktracer.dto.OpenWelfareRecord;
import com.stu.worktracer.es.ESService;
import com.stu.worktracer.es.type.CheckRecord;
import com.stu.worktracer.es.type.Company;
import com.stu.worktracer.es.type.WelfareRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAPIService {

    @Autowired
    private ESService esService;

    public List<OpenCheckRecord> queryCheck(Long companyId, long from, long to, int page, int size) {
        List<CheckRecord> esList = esService.queryTimePage("check_record", companyId, CheckRecord.class, "checkInTime", from, to, page, size);
        List<OpenCheckRecord> resList = new ArrayList<>(esList.size());
        for (CheckRecord record : esList) {
            OpenCheckRecord openRecord = new OpenCheckRecord();
            openRecord.setCompanyId(record.getCompanyId());
            openRecord.setCompanyName(record.getCompanyName());
            openRecord.setCheckInTime(record.getCheckInTime());
            openRecord.setCheckOutTime(record.getCheckOutTime());
            resList.add(openRecord);
        }
        return resList;
    }

    public List<OpenWelfareRecord> queryWelfare(Long companyId, long from, long to, int page, int size) {
        List<WelfareRecord> esList = esService.queryTimePage("welfare_record", companyId, WelfareRecord.class, "checkInTime", from, to, page, size);
        List<OpenWelfareRecord> resList = new ArrayList<>(esList.size());
        for (WelfareRecord record : esList) {
            OpenWelfareRecord openRecord = new OpenWelfareRecord();
            openRecord.setCompanyId(record.getCompanyId());
            openRecord.setCompanyName(record.getCompanyName());
            openRecord.setWelfare(record.getWelfare());
            openRecord.setSurveyTime(record.getSurveyTime());
            resList.add(openRecord);
        }
        return resList;
    }

    public List<OpenCompany> queryCompany(String search, int page, int size) {
        List<Company> esList = esService.searchCompany(search, page, size);
        List<OpenCompany> list = new ArrayList<>(esList.size());
        for (Company company : esList) {
            OpenCompany oc = new OpenCompany();
            oc.setCompanyId(company.getCompanyId());
            oc.setName(company.getName());
            oc.setWorkshop(company.getWorkshop());
            list.add(oc);
        }
        return list;
    }
}
