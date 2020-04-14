package com.stu.worktracer.service;

import com.stu.worktracer.dao.Company;
import com.stu.worktracer.dao.CompanyMapper;
import com.stu.worktracer.dao.ToApprove;
import com.stu.worktracer.dao.ToApproveMapper;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    private final ToApproveMapper toApproveMapper;

    private final CompanyMapper companyMapper;

    public AdminService(ToApproveMapper toApproveMapper, CompanyMapper companyMapper) {
        this.toApproveMapper = toApproveMapper;
        this.companyMapper = companyMapper;
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
    }

    @Override
    public void decline(Long id) throws KnownException {
        toApproveMapper.deleteToApprove(id);
    }
}
