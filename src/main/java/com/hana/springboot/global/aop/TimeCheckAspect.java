package com.hana.springboot.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TimeCheckAspect {

    @Before("@annotation(com.hana.springboot.global.aop.annotation.TimeCheck)")
    public void timeCheck(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        log.info("START ==> {} ",joinPoint.getSignature().getName());
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("FINISH ==> {}  resultTime = {}",joinPoint.getSignature().getName(), resultTime);
    }
}
