package com.stu.worktracer.controller;

import com.stu.worktracer.dto.CompanyIdRequest;
import com.stu.worktracer.dto.LoginRequest;
import com.stu.worktracer.dto.RegisterRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.utils.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping("/checkcom")
    public ResponseDTO checkCom() {
        return null;
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginRequest request) {
        String valid = request.validate();
        if (valid != null) {
            return ResponseUtil.errRes(valid);
        }

        return null;

    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody RegisterRequest request) {
        String valid = request.validate();
        if (valid != null) {
            return ResponseUtil.errRes(valid);
        }

        return null;
    }

    @PostMapping("/modifycom")
    public ResponseDTO modifyCom(@RequestBody CompanyIdRequest request) {
        return null;
    }


}
