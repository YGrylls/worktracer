package com.stu.worktracer.controller;

import com.stu.worktracer.dao.ToApprove;
import com.stu.worktracer.dto.CompanyIdRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.dto.SearchRequest;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.service.AdminServiceInterface;
import com.stu.worktracer.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AdminController extends BaseController {

    @Autowired
    private AdminServiceInterface adminService;

    @PostMapping("/admin-queryunapproved")
    public ResponseDTO adminQueryUnapproved(HttpServletRequest httpRequest, @RequestBody SearchRequest request) throws KnownException {
        List<ToApprove> list = adminService.getList(request.getPage(), request.getSize());
        return ResponseUtil.sucRes(list);
    }

    @PostMapping("/admin-approve")
    public ResponseDTO adminApprove(HttpServletRequest httpRequest, @RequestBody CompanyIdRequest request) throws KnownException {
        adminService.approve(request.getCompanyId());
        return ResponseUtil.sucRes("ok");
    }

    @PostMapping("/admin-decline")
    public ResponseDTO adminDecline(HttpServletRequest httpRequest, @RequestBody CompanyIdRequest request) throws KnownException {
        adminService.decline(request.getCompanyId());
        return ResponseUtil.sucRes("ok");
    }
}
