package com.hana.springboot.global.configuration;

import com.hana.springboot.global.aop.TimeCheckAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    /**
     * 로직 경과시간 확인 Aspect
     * @TimeCheck로 실행
     */
    @Bean
    public TimeCheckAspect timeCheckAspect() {
        return new TimeCheckAspect();
    }
}
