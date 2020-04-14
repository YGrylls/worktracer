package com.stu.worktracer.service;

import com.stu.worktracer.dao.Company;
import com.stu.worktracer.dao.CompanyMapper;
import com.stu.worktracer.dao.ToApprove;
import com.stu.worktracer.dao.ToApproveMapper;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.es.ESService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    private final ToApproveMapper toApproveMapper;

    private final CompanyMapper companyMapper;

    private final ESService esService;

    public AdminService(ToApproveMapper toApproveMapper, CompanyMapper companyMapper, ESService esService) {
        this.toApproveMapper = toApproveMapper;
        this.companyMapper = companyMapper;
        this.esService = esService;
    }

    @Override
    public List<ToApprove> getList(int page, int size) throws KnownException {
        return toApproveMapper.getToApproveList(page, size);
    }

    @Override
    @Transactional
    public void approve(Long id) throws KnownException {
        ToApprove toApprove = toApproveMapper.getToApproveById(id);
        if (toApprove == null) {
            throw new KnownException(ErrCode.COMPANY_NOT_EXIST_ERROR);
        }
        Company nc = new Company();
        nc.setName(toApprove.getCompanyName());
        nc.setWorkshop(toApprove.getWorkshop());
        nc.setRate(0);
        nc.setWelfare(0);
        companyMapper.addCompany(nc);
        toApproveMapper.deleteToApprove(id);
        com.stu.worktracer.es.type.Company esCompany = new com.stu.worktracer.es.type.Company();
        esCompany.setWorkshop(toApprove.getWorkshop());
        esCompany.setName(toApprove.getCompanyName());
        esCompany.setCompanyId(nc.getCompanyId());
        esService.indexCompany(esCompany);
    }

    @Override
    public void decline(Long id) throws KnownException {
        toApproveMapper.deleteToApprove(id);
    }
}
