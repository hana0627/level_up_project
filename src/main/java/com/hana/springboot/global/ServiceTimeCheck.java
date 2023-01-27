// proxy 적용 시도를 위한 class
// 실패하였음

//package com.hana.springboot.global;
//
//import com.hana.springboot.data.domain.dto.MemberLoginDto;
//import com.hana.springboot.data.service.MemberService;
//import lombok.extern.slf4j.Slf4j;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
//@Slf4j
//public class ServiceTimeCheck implements InvocationHandler {
//
//    MemberService target;
//
//    public ServiceTimeCheck(MemberService target) {
//        this.target = target;
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        log.info("START ==> {} ",method);
//
//        MemberLoginDto result = (MemberLoginDto) method.invoke(target, args);
//        long endTime = System.currentTimeMillis();
//        long resultTime = endTime - startTime;
//        log.info("FINISH ==> {}  resultTime = {}",method, resultTime);
//        return result;
//    }
//}
