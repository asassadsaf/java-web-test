package com.fkp.javawebtest.requestwrapper.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description 拦截器，用来项request对象中设置参数等操作
 * @date 2024/4/29 15:38
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request instanceof MyHttpServletRequestWrapper requestWrapper){
            requestWrapper.setParameter("tenantAccount", "zuhu100");
//            requestWrapper.setParameter("tenantAccount", "zuhu1");
//            String tenantCode = requestWrapper.getHeader("tenantCode");
//            System.out.println(tenantCode);
//            requestWrapper.setHeader("tenantCode", "zuhu2");
//            System.out.println(requestWrapper.getHeader("tenantCode"));
//            requestWrapper.setHeader("tenantCode", "zuhu3");
//            System.out.println(requestWrapper.getHeader("tenantCode"));
        }else if(request instanceof StandardMultipartHttpServletRequest multipartHttpServletRequest &&
                multipartHttpServletRequest.getRequest() instanceof MyHttpServletRequestWrapper requestWrapper){
            requestWrapper.setParameter("tenantAccount", "zuhu_fkp");
            requestWrapper.setParameter("user", "test");
        }

        return true;
    }
}
