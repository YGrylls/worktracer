package com.stu.worktracer.aop;

import com.stu.worktracer.error.ErrCode;
import com.stu.worktracer.error.KnownException;
import com.stu.worktracer.utils.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ExceptionHandler {

    @Pointcut("execution(* com.stu.worktracer.controller..*.*(..))")
    public void exceptionCatcher() {

    }

    @Around("exceptionCatcher()")
    public Object handleException(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (KnownException ke) {
            //TODO change to log
            return ResponseUtil.errRes(ke.getErrCode(), ke.getMsg());
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseUtil.errRes(ErrCode.INTERNAL_SERVER_ERROR, t.getMessage());
        }
    }

}
