package com.stu.worktracer.controller;

import com.stu.worktracer.dto.CheckInOutRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.dto.SurveyRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public class CheckController extends BaseController {

    @PostMapping("/checkin")
    public ResponseDTO checkIn(HttpServletRequest httpRequest, @RequestBody CheckInOutRequest request) {
        return null;
    }

    @PostMapping("/checkout")
    public ResponseDTO checkOut(HttpServletRequest httpRequest, @RequestBody CheckInOutRequest request) {
        return null;
    }

    @PostMapping("/survey")
    public ResponseDTO survey(HttpServletRequest httpRequest, @RequestBody SurveyRequest request) {
        return null;
    }

}
