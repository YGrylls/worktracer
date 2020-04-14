package com.stu.worktracer.controller;

import com.stu.worktracer.aop.Auth;
import com.stu.worktracer.dto.*;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.service.CompanyServiceInterface;
import com.stu.worktracer.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CompanyController extends BaseController {

    @Autowired
    private CompanyServiceInterface companyService;

    @PostMapping("/getCompanyList")
    public ResponseDTO getCompanyList(HttpServletRequest httpRequest, @RequestBody SearchRequest request) throws KnownException {
        if (!request.validate()) {
            return ResponseUtil.errRes(ErrCode.REQUEST_FORMAT_ERROR);
        }
        List<SimpleCompany> res = companyService.getCompanyList(request.getSearch(), request.getPage(), request.getSize());
        return ResponseUtil.sucRes(res);
    }

    @PostMapping("/getCompanyInfo")
    public ResponseDTO getCompanyInfo(HttpServletRequest httpRequest, @RequestBody CompanyIdRequest request) throws KnownException {
        DetailCompany dc = companyService.getCompanyInfo(request.getCompanyId());
        return ResponseUtil.sucRes(dc);
    }

    @Auth
    @PostMapping("/submitnewcom")
    public ResponseDTO submitNewCom(HttpServletRequest httpRequest, @RequestBody NewCompanyRequest request) throws KnownException {
        companyService.submitNewCom(request.getCompanyName(), request.getWorkshop());
        return ResponseUtil.sucRes("ok");
    }

}
