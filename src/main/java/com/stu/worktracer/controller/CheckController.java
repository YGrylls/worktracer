package com.stu.worktracer.controller;

import com.stu.worktracer.aop.Auth;
import com.stu.worktracer.dto.CheckInOutRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.dto.SurveyRequest;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.service.CheckServiceInterface;
import com.stu.worktracer.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CheckController extends BaseController {

    @Autowired
    private CheckServiceInterface checkService;

    @Auth
    @PostMapping("/checkin")
    public ResponseDTO checkIn(HttpServletRequest httpRequest, @RequestBody CheckInOutRequest request) throws KnownException {
        checkService.checkIn(getUid());
        return ResponseUtil.sucRes("ok");
    }

    @Auth
    @PostMapping("/checkout")
    public ResponseDTO checkOut(HttpServletRequest httpRequest, @RequestBody CheckInOutRequest request) throws KnownException {
        checkService.checkOut(getUid());
        return ResponseUtil.sucRes("ok");
    }

    @Auth
    @PostMapping("/survey")
    public ResponseDTO survey(HttpServletRequest httpRequest, @RequestBody SurveyRequest request) throws KnownException {
        int welfare = request.getWelfare();
        if (welfare < 0 || welfare > 10) {
            throw new KnownException(ErrCode.REQUEST_FORMAT_ERROR);
        }
        checkService.survey(getUid(), request.getWelfare());
        return ResponseUtil.sucRes("ok");
    }

    @Auth
    @PostMapping("/checkcheck")
    public ResponseDTO checkCheck(HttpServletRequest httpRequest) throws KnownException {
        Integer res = checkService.checkCheck(getUid());
        return ResponseUtil.sucRes(res);
    }

    @Auth
    @PostMapping("/checksurvey")
    public ResponseDTO checkSurvey(HttpServletRequest httpRequest) throws KnownException {
        Boolean res = checkService.checkSurvey(getUid());
        return ResponseUtil.sucRes(res);
    }
}
