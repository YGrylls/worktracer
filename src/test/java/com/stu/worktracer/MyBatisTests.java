package com.stu.worktracer;

import com.stu.worktracer.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MyBatisTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CheckInOutMapper checkInOutMapper;


    @Test
    void testCompanyMapper() {
        Company com = new Company();
        com.setName("Root Inc");
        com.setWorkshop("上海");
        Long res = companyMapper.addCompany(com);
        System.out.println(res);
    }

    @Test
    void testCheckMapper() {
        long now = new Date().getTime();
        CheckInOut rec = new CheckInOut();
        rec.setCheckIn(new Date(now));
        rec.setCompanyId(3L);
        rec.setUid(1L);
        rec.setPercentFix(100);
        int res0 = checkInOutMapper.addCheckRecord(rec);
        System.out.println(res0);
        int res1 = checkInOutMapper.updateCheckOut(1L, new Date(now + 3600 * 8 * 1000L));
        System.out.println(res1);
        List<CheckInOut> list = checkInOutMapper.getAllCheckRecord(0, 10);
        System.out.println(list);
    }

    @Test
    void testInClause() {
        List<Long> ids = new ArrayList<>();
        ids.add(8L);
        List<Company> res = companyMapper.getCompanyByList(ids);
        System.out.println(res);
    }

}
