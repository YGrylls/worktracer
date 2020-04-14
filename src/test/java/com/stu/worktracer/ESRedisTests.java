package com.stu.worktracer;

import com.stu.worktracer.es.ESService;
import com.stu.worktracer.es.type.CheckRecord;
import com.stu.worktracer.es.type.Company;
import com.stu.worktracer.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ESRedisTests {

    @Test
    void contextLoads() {

    }


    @Autowired
    private ESService esService;

    @Test
    public void testCreateIndex() {
        esService.createIndex("company", Company.class);
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
        company.setCompanyId(8L);
        company.setName("测试计算机有限公司");
        company.setWorkshop("百京");
        esService.indexCompany(company);
    }

    @Test
    public void testSearchCompany() {
        List<Company> res0 = esService.searchCompany("弟弟", 0, 10);
        List<Company> res1 = esService.searchCompany("didiyin", 0, 10);
        System.out.println(res0);
        System.out.println(res1);
    }

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis() {

    }

}
