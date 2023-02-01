package com.hana.springboot.global.interceptor;


import com.hana.springboot.data.domain.eunmClass.MemberType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 판매자로 등록되지 않은 사람은
 * 판매자용 페이지에 접근할 수 없도록 interceptor 적용하였음
 */
public class SellerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("member").equals(MemberType.SELLER)) {
            response.sendRedirect("/userHome");
            return false;
        }
        return true;
    }
}
