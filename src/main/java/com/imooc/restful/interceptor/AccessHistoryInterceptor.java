package com.imooc.restful.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessHistoryInterceptor implements HandlerInterceptor {
    private Logger logger= LoggerFactory.getLogger(AccessHistoryInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer sb=new StringBuffer();
        //远程用户的ip地址：可分析用户来自那个省市
        sb.append(request.getRemoteAddr());
        sb.append("|");
         //用户访问的url地址
        sb.append(request.getRequestURL());
        sb.append("|");
        //用户的客户端环境
        sb.append(request.getHeader("user-agent"));
        logger.info(sb.toString());
        return true;
    }
}
