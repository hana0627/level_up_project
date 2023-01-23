package com.hana.springboot.global.configuration;

import com.hana.springboot.global.aop.TimeCheckAspect;
import com.hana.springboot.global.formatter.DateTypeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //private final DefaultFormattingConversionService conversionService;


    /**
     * 로직 경과시간 확인 Aspect
     * @TimeCheck로 실행
     */
    @Bean
    public TimeCheckAspect timeCheckAspect() {
        return new TimeCheckAspect();
    }

    /**
     * String -> LocalDateTime formatter
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateTypeFormatter());
    }


    }
