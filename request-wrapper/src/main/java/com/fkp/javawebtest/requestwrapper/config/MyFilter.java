package com.fkp.javawebtest.requestwrapper.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2024/4/29 10:56
 */
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        MyHttpServletRequestWrapper myHttpServletRequestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);

        chain.doFilter(myHttpServletRequestWrapper, response);

    }
}
