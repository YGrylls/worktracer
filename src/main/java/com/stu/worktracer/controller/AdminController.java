package com.stu.worktracer.controller;

import com.stu.worktracer.dto.CompanyIdRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.dto.SearchRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @PostMapping("/admin-queryunapproved")
    public ResponseDTO adminQueryUnapproved(SearchRequest request){
        return null;
    }

    @PostMapping("/admin-approve")
    public ResponseDTO adminApprove(CompanyIdRequest request){
        //this company id is unapproved one's
        return null;
    }
}
