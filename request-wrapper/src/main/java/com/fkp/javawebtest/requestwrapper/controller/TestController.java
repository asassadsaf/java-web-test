package com.fkp.javawebtest.requestwrapper.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fkp.javawebtest.requestwrapper.eneity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description
 * @date 2024/4/29 9:50
 */
@RestController
public class TestController {

    /**
     * 接收Get方法参数，@RequestParam可加可不加
     * @param name 单个参数
     * @param tenantAccount 单个参数
     * @return 响应字符串
     */
    @GetMapping(value = "/hello")
    public String hello(@RequestParam(name = "name") String name, String tenantAccount){
        return "hello web!" + "name: " + name + " tenantAccount: " + tenantAccount;
    }

    /**
     * 接收raw: text(json) json格式参数
     * @param user 实体类参数
     * @return 实体类响应
     */
    @PostMapping(value = "/save")
    public User save(@RequestBody User user){
        return user;
    }

    /**
     * 接收Post(x-www-form-urlencoded)请求参数不需要加任何注解（单个参数或实体类）
     * @param user 实体类参数
     * @param addr 单个参数
     * @return 拼接后的json字符串
     */
    @PostMapping(value = "/save2")
    public String save2(User user, String addr){
        JSONObject object = JSONObject.from(user);
        object.put("addr", addr);
        return object.toString();
    }


    @PostMapping(value = "save3")
    public String save3(User user, MultipartFile file){
        return JSON.toJSONString(user);
    }
}
