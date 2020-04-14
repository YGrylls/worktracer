package com.stu.worktracer.controller;

import com.stu.worktracer.aop.Auth;
import com.stu.worktracer.dto.*;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.service.UserServiceInterface;
import com.stu.worktracer.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends BaseController {


    @Autowired
    private UserServiceInterface userService;

    @Auth
    @PostMapping("/checkcom")
    public ResponseDTO checkCom(HttpServletRequest httpRequest) throws KnownException {
        Long uid = getUid();
        CheckCompanyRes res = userService.checkCom(uid);
        return ResponseUtil.sucRes(res);
    }

    @PostMapping("/login")
    public ResponseDTO login(HttpServletRequest httpRequest, @RequestBody LoginRequest request) throws KnownException {
        String valid = request.validate();
        if (valid != null) {
            return ResponseUtil.errRes(valid);
        }
        String token = userService.login(request.getUsername(), request.getPw());
        return ResponseUtil.sucRes(token);
    }

    @PostMapping("/register")
    public ResponseDTO register(HttpServletRequest httpRequest, @RequestBody RegisterRequest request) throws KnownException {
        String valid = request.validate();
        if (valid != null) {
            return ResponseUtil.errRes(valid);
        }
        String token = userService.register(request.getUsername(), request.getPw());
        return ResponseUtil.sucRes(token);
    }

    @Auth
    @PostMapping("/modifycom")
    public ResponseDTO modifyCom(HttpServletRequest httpRequest, @RequestBody CompanyIdRequest request) throws KnownException {
        Long uid = getUid();
        userService.modifyCom(uid, request.getCompanyId());
        return ResponseUtil.sucRes("ok");
    }


}
