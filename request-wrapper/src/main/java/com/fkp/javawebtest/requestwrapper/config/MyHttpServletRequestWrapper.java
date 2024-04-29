package com.fkp.javawebtest.requestwrapper.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.catalina.util.ParameterMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.MimeHeaders;

import java.util.*;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2024/4/29 10:12
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final ParameterMap<String, String[]> parameterMap;

    private final MimeHeaders headers;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        parameterMap = new ParameterMap<>();
        headers = new MimeHeaders();
    }

    /**
     * 获取Get请求参数，若存在同名key，优先级 包装 > 原生
     * @param name 参数名
     * @return 参数值
     */
    @Override
    public String getParameter(String name) {
        String[] values = parameterMap.get(name);
        String realValue = values == null ? null : values.length > 0 ? values[0] : "";
        return Optional.ofNullable(realValue).orElse(super.getParameter(name));
    }

    /**
     * 获取所有Get请求参数， 若存在同名key，优先级 包装 > 原生
     * @return 参数值
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        //若存在相同key，super.getParameterMap()覆盖parameterMap
        Map<String, String[]> res = new LinkedHashMap<>();
        res.putAll(super.getParameterMap());
        res.putAll(parameterMap);
        return res;
    }

    /**
     * 获取Get所有参数名
     * @return 所有参数名的Enumeration类型
     */
    @Override
    public Enumeration<String> getParameterNames() {
        HashSet<String> set = new HashSet<>();
        Enumeration<String> parameterNames = super.getParameterNames();
        while (parameterNames.hasMoreElements()){
            set.add(parameterNames.nextElement());
        }
        set.addAll(parameterMap.keySet());
        return Collections.enumeration(set);
    }

    /**
     * 获取Get请求参数的值,若存在同名key，优先级 包装 > 原生
     * Get请求在映射到Controller参数之前会调用该方法取参数
     * @param name 参数名
     * @return 参数值数组
     */
    @Override
    public String[] getParameterValues(String name) {
        return Optional.ofNullable(parameterMap.get(name)).orElse(super.getParameterValues(name));
    }

    /**
     * 设置Get请求参数
     * @param name 参数名
     * @param value 参数值
     */
    public void setParameter(String name, String value){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("set parameter: name can not blank.");
        }
        parameterMap.put(name, new String[]{value});
    }

    /**
     * 获取请求头的值，若存在相同名称，优先级 包装 > 原生
     * @param name 参数名
     * @return 参数值
     */
    @Override
    public String getHeader(String name) {
        return Optional.ofNullable(headers.getHeader(name)).orElse(super.getHeader(name));
    }

    /**
     * 获取请求头的值，可能为多个，若存在相同名称，优先级 包装 > 原生
     * @param name 参数名
     * @return 参数值
     */
    @Override
    public Enumeration<String> getHeaders(String name) {
        return Optional.ofNullable(headers.values(name)).orElse(super.getHeaders(name));
    }

    /**
     * 获取所有请求头名称，去重
     * @return 所有去重后的请求头名称
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> set = new HashSet<>();
        Enumeration<String> headerNamesEnum = headers.names();
        Enumeration<String> headerNamesEnum2 = super.getHeaderNames();
        while (headerNamesEnum.hasMoreElements()){
            set.add(headerNamesEnum.nextElement());
        }
        while (headerNamesEnum2.hasMoreElements()){
            set.add(headerNamesEnum2.nextElement());
        }
        return Collections.enumeration(set);
    }

    /**
     * 设置请求头，若名称重复则覆盖
     * @param name 参数名
     * @param value 参数值，若已经存在相同的name则覆盖原来的value
     */
    public void setHeader(String name, String value){
        if(StringUtils.isBlank(name)){
            throw new IllegalArgumentException("set parameter: name can not blank.");
        }
        MessageBytes messageBytes = headers.setValue(name);
        messageBytes.setString(value);

    }



}
