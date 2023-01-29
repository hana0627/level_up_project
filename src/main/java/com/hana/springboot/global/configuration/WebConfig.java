package com.hana.springboot.global.configuration;

import com.hana.springboot.global.aop.TimeCheckAspect;
import com.hana.springboot.global.formatter.DateTypeFormatter;
import com.hana.springboot.global.interceptor.AdminCheckInterceptor;
import com.hana.springboot.global.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {



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

    /**
     * Interceptor 적용
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                // 새로 개발되는 모든 항목에 대해선 일단 로그인 된 사용자만 이용하게끔 의도하였으나
                // 페이지가 늘어날때마다 excludePathPatterns가 늘어나는 현상 발생. 안티패턴으로 추측됨
                // 추후엔 addPathPatterns를 좀 더 구체적으로 지정해주어야 할 것 같음 
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/new","/members/LoginIdDuplicate","/members/login","*css/**"
                                    ,"/*.ico","/error","/members/memberList");


        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/members/memberList")
                .excludePathPatterns("/","*css/**","/*.ico","/error");
    }
}
