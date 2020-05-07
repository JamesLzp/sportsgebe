package com.guet.sportsgebe.interceptor.handleInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginHandleInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        //重定向
        Object object = request.getSession().getAttribute("name");
        if (null == object) {
            System.out.println("自动登录失败!!");
            out.write("false!");
            out.flush();
            out.close();
            return false;
        }
        System.out.println("自动登录放行!!");
        return true;
    }
}
