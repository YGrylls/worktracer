package com.stu.worktracer.service;

import com.stu.worktracer.dao.Company;
import com.stu.worktracer.dao.CompanyMapper;
import com.stu.worktracer.dao.ToApprove;
import com.stu.worktracer.dao.ToApproveMapper;
import com.stu.worktracer.dto.DetailCompany;
import com.stu.worktracer.dto.SimpleCompany;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.es.ESService;
import com.stu.worktracer.redis.RedisService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class CompanyService implements CompanyServiceInterface {

    private final CompanyMapper companyMapper;

    private final ESService esService;

    private final RedisService redisService;

    private final ToApproveMapper toApproveMapper;

    private final RatingServiceInterface ratingService;

    public CompanyService(CompanyMapper companyMapper, ESService esService, RedisService redisService, ToApproveMapper toApproveMapper, RatingServiceInterface ratingService) {
        this.companyMapper = companyMapper;
        this.esService = esService;
        this.redisService = redisService;
        this.toApproveMapper = toApproveMapper;
        this.ratingService = ratingService;
    }

    @Override
    public List<SimpleCompany> getCompanyList(String search, int page, int size) throws KnownException {
        List<com.stu.worktracer.es.type.Company> esList = esService.searchCompany(search, page, size);
        List<Long> ids = new ArrayList<>(esList.size());
        for (com.stu.worktracer.es.type.Company c : esList) {
            ids.add(c.getCompanyId());
        }
        List<Company> list = companyMapper.getCompanyByList(ids);
        list.sort(Comparator.comparingInt(Company::getRate));
        List<SimpleCompany> res = new ArrayList<>(list.size());
        for (Company c : list) {
            SimpleCompany nc = new SimpleCompany();
            nc.setCompanyName(c.getName());
            nc.setCompanyId(c.getCompanyId());
            nc.setRate(c.getRate());
            res.add(nc);
        }
        return res;
    }

    @Override
    public DetailCompany getCompanyInfo(Long companyId) throws KnownException {
        //TODO
        DetailCompany dc = new DetailCompany();
        Company company = companyMapper.getCompanyById(companyId);
        if (company == null) {
            throw new KnownException(ErrCode.COMPANY_NOT_EXIST_ERROR);
        }
        dc.setCompanyId(company.getCompanyId());
        dc.setName(company.getName());
        dc.setWorkshop(company.getWorkshop());
        dc.setTotalRate(company.getRate());
        dc.setWelfare(company.getWelfare());
        // TODO
        dc.setOffWorkTimeMonth(0L);
        dc.setOffWorkTimeWeek(0L);
        dc.setStartWorkTimeMonth(0L);
        dc.setStartWorkTimeWeek(0L);
        return dc;
    }

    @Override
    public void submitNewCom(String companyName, String workshop) throws KnownException {
        Company exist = companyMapper.getCompanyByName(companyName);
        if (exist != null) {
            throw new KnownException(ErrCode.COMPANY_EXIST_ERROR);
        }
        ToApprove existTo = toApproveMapper.getToApproveByName(companyName);
        if (existTo != null) {
            throw new KnownException(ErrCode.COMPANY_EXIST_ERROR);
        }
        ToApprove newC = new ToApprove();
        newC.setCompanyName(companyName);
        newC.setWorkshop(workshop);
        newC.setSubmitTime(new Date());
        toApproveMapper.addToApprove(newC);
    }
}
