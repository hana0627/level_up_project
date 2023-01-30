package com.hana.springboot.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TimeCheckAspect {


    /**
     * 로직 경과시간 확인 Aspect
     * @TimeCheck로 실행
     */
//    @Around("@annotation(com.hana.springboot.global.aop.annotation.TimeCheck)")
//    public Object timeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        log.info("START ==> {} ",joinPoint.getSignature().getName());
//        Object proceed = joinPoint.proceed();
//        long endTime = System.currentTimeMillis();
//        long resultTime = endTime - startTime;
//        log.info("FINISH ==> {}  resultTime = {}",joinPoint.getSignature().getName(), resultTime);
//        return proceed;
//    }


    /**
     * 로직 경과시간 확인 Aspect
     * AspectJExpressionPointcut사용
     * 모든 서비스 로직에 적용
     */
    @Around("execution(* com.hana.springboot.data.service.*.*(..))")
    public Object timeCheckExpression(ProceedingJoinPoint joinPoint)throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("START ==> {} ",joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("FINISH ==> {}  resultTime = {}",joinPoint.getSignature().getName(), resultTime);
        return proceed;
    }
    

}
