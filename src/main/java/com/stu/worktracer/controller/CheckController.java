package com.stu.worktracer.controller;

import com.stu.worktracer.dto.CheckInOutRequest;
import com.stu.worktracer.dto.ResponseDTO;
import com.stu.worktracer.dto.SurveyRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CheckController {

    @PostMapping("/checkin")
    public ResponseDTO checkIn(@RequestBody CheckInOutRequest request){
        return null;
    }

    @PostMapping("/checkout")
    public ResponseDTO checkOut(@RequestBody CheckInOutRequest request){
        return null;
    }

    @PostMapping("/survey")
    public ResponseDTO survey(@RequestBody SurveyRequest request){
        return null;
    }

}
