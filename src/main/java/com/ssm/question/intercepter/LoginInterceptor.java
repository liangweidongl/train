package com.ssm.question.intercepter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (response.isCommitted()) {//如果响应已经处理过了，就返回
            return true;
        }
        if (request.getSession().getAttribute("SESSION_USER") == null) {
            response.sendRedirect("/login.jsp");
            return false;
        }
        //返回true表示可以进入controller,返回false代表请求被拦截
        return true;
    }
}
