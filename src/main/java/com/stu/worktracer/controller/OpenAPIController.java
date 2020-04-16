package com.stu.worktracer.controller;


import com.stu.worktracer.dto.*;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.redis.RedisService;
import com.stu.worktracer.service.OpenAPIService;
import com.stu.worktracer.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OpenAPIController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private OpenAPIService openAPIService;

    @GetMapping("/openapi-query-company")
    public ResponseDTO queryCompany(HttpServletRequest httpRequest, @RequestBody SearchRequest request) throws KnownException {
        if (!request.validate()) {
            throw new KnownException(ErrCode.REQUEST_FORMAT_ERROR);
        }
        String client = httpRequest.getRemoteAddr();
        boolean flag = redisService.getSetOpenCD(client);
        if (flag) {
            List<OpenCompany> list = openAPIService.queryCompany(request.getSearch(), request.getPage(), request.getSize());
            return ResponseUtil.sucRes(list);
        } else {
            return ResponseUtil.errRes(ErrCode.OPEN_API_COLD_DOWN);
        }
    }

    @GetMapping("/openapi-query-checkrecord")
    public ResponseDTO queryCheckRecord(HttpServletRequest httpRequest, @RequestBody OpenQueryRequest request) throws KnownException {
        if (!request.validate()) {
            throw new KnownException(ErrCode.REQUEST_FORMAT_ERROR);
        }
        String client = httpRequest.getRemoteAddr();
        boolean flag = redisService.getSetOpenCD(client);
        if (flag) {
            List<OpenCheckRecord> list = openAPIService.queryCheck(request.getCompanyId(), request.getFrom(), request.getTo(), request.getPage(), request.getSize());
            return ResponseUtil.sucRes(list);
        } else {
            return ResponseUtil.errRes(ErrCode.OPEN_API_COLD_DOWN);
        }
    }

    @GetMapping("/openapi-query-welfarerecord")
    public ResponseDTO queryWelfareRercord(HttpServletRequest httpRequest, @RequestBody OpenQueryRequest request) throws KnownException {
        if (!request.validate()) {
            throw new KnownException(ErrCode.REQUEST_FORMAT_ERROR);
        }
        String client = httpRequest.getRemoteAddr();
        boolean flag = redisService.getSetOpenCD(client);
        if (flag) {
            List<OpenWelfareRecord> list = openAPIService.queryWelfare(request.getCompanyId(), request.getFrom(), request.getTo(), request.getPage(), request.getSize());
            return ResponseUtil.sucRes(list);
        } else {
            return ResponseUtil.errRes(ErrCode.OPEN_API_COLD_DOWN);
        }
    }
}
