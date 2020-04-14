package com.stu.worktracer.aop;

import com.stu.worktracer.controller.BaseController;
import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.redis.RedisService;
import com.stu.worktracer.utils.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class AuthHandler {
    @Pointcut("@annotation(com.stu.worktracer.aop.Auth)")
    public void authPointcut() {
    }

    @Autowired
    private RedisService redisService;

    private static String TOKEN_KEY = "token";

    @Around("authPointcut()")
    public Object authHandle(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        String token = request.getHeader(TOKEN_KEY);
        Long uid = redisService.getUid(token);
        if (uid == null) {
            return ResponseUtil.errRes(ErrCode.AUTH_ERROR);
        }
        BaseController ctrl = (BaseController) pjp.getTarget();
        ctrl.setUid(uid);
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResponseUtil.errRes(ErrCode.INTERNAL_SERVER_ERROR, throwable.getMessage());
        }
    }
}
