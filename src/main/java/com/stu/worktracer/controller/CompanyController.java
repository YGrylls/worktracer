package com.stu.worktracer.controller;

import com.stu.worktracer.dto.CompanyIdRequest;
import com.stu.worktracer.dto.NewCompanyRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.dto.SearchRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @PostMapping("/getCompanyList")
    public ResponseDTO getCompanyList(@RequestBody SearchRequest request){
        return null;
    }

    @PostMapping("/getCompanyInfo")
    public ResponseDTO getCompanyInfo(@RequestBody CompanyIdRequest request){
        return null;
    }

    @PostMapping("/submitnewcom")
    public ResponseDTO submitNewCom(@RequestBody NewCompanyRequest request){
        return null;
    }

}
