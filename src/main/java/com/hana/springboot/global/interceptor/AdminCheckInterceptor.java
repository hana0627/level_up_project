//package com.hana.springboot.global.interceptor;
//
//        import com.hana.springboot.data.domain.eunmClass.MemberStatus;
//        import lombok.extern.slf4j.Slf4j;
//        import org.springframework.web.servlet.HandlerInterceptor;
//
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//        import javax.servlet.http.HttpSession;
//
//@Slf4j
//public class AdminCheckInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        HttpSession session = request.getSession(false);
//
//        if(session == null || session.getAttribute("member").equals(MemberStatus.ADMIN)) {
//            response.sendRedirect("/members/notAdmin");
//            return false;
//        }
//        return true;
//    }
//}
