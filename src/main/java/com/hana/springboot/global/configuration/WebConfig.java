package com.hana.springboot.global.configuration;

import com.hana.springboot.global.aop.TimeCheckAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public TimeCheckAspect timeCheckAspect() {
        return new TimeCheckAspect();
    }
}
