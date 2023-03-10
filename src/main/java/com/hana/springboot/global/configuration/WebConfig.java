package com.hana.springboot.global.configuration;

import com.hana.springboot.global.aop.TimeCheckAspect;
import com.hana.springboot.global.formatter.DateTypeFormatter;
import com.hana.springboot.global.interceptor.LoginCheckInterceptor;
import com.hana.springboot.global.interceptor.SellerInterceptor;
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

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/members/new", "/members/LoginIdDuplicate", "/members/login", "*css/**"
//                        , "/*.ico", "/error");
//
//        registry.addInterceptor(new SellerInterceptor())
//                .order(2)
//                .addPathPatterns("/products/sellers/*")
//                .excludePathPatterns("*css/**", "/*.ico", "/error");
//    }


//        registry.addInterceptor(new AdminCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/members/memberList")
//                .excludePathPatterns("/","*css/**","/*.ico","/error");
//    }
}
