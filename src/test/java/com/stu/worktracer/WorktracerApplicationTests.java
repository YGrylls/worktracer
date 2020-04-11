package com.stu.worktracer;

import com.stu.worktracer.es.ESService;
import com.stu.worktracer.es.type.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorktracerApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private ESService esService;
    @Test
    public void testCreateIndex(){
        esService.createIndex();
    }

    @Test
    public void testIndex(){
        Company company=new Company();
        company.setCompanyId(0L);
        company.setName("Test Inc");
        company.setWorkshop("上海");
        esService.index("company",company);
    }

}
