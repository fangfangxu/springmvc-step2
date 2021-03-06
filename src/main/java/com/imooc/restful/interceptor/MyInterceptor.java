package com.imooc.restful.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
       System.out.println(request.getRequestURL()+
               "MyInterceptor-前置处理:在HandlerAdapter.handle()方法之前被调用");
//       response.getWriter().print("[1]");
       return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(request.getRequestURL()+
                "MyInterceptor-目标处理成功：在HandlerAdapter.handle()方法之后，视图渲染之前被调用");
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println(request.getRequestURL()+
                "MyInterceptor-响应内容已产生：视图渲染之后被调用");
    }
}
