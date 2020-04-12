package com.stu.worktracer;

import com.stu.worktracer.es.ESService;
import com.stu.worktracer.es.type.CheckRecord;
import com.stu.worktracer.es.type.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class WorktracerApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private ESService esService;

    @Test
    public void testCreateIndex() {
        esService.createIndex("check_record", CheckRecord.class);
    }

    @Test
    public void testIndexCheckRecord() {
        CheckRecord record = new CheckRecord();
        record.setCompanyId(0L);
        record.setCompanyName("Nook Inc");
        record.setUid(0L);
        record.setUsername("Tom Nook");
        record.setCheckInTime(Calendar.getInstance().getTimeInMillis() - 3600 * 40 * 1000L);
        record.setCheckOutTime(Calendar.getInstance().getTimeInMillis() - 3600 * 32 * 1000L);
        esService.indexCheckRecordBulk(Collections.singletonList(record));
    }

    @Test
    public void testQueryCheckRecord() {
        List<CheckRecord> res0 = esService.queryCheckRecordWeek(0L);
        System.out.println(res0);
    }

    @Test
    public void testIndexCompany() {
        Company company = new Company();
        company.setCompanyId(1L);
        company.setName("臭弟弟公司");
        company.setWorkshop("阴间");
        esService.indexCompany(company);
    }

    @Test
    public void testSearchCompany() {
        List<Company> res0 = esService.searchCompany("弟弟", 0, 10);
        List<Company> res1 = esService.searchCompany("didiyin", 0, 10);
        System.out.println(res0);
        System.out.println(res1);
    }

}
