package com.fkp.javawebtest.requestwrapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
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

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(name = "name") String name, String tenantAccount){
        return "hello web!" + "name: " + name + " tenantAccount: " + tenantAccount;
    }

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        Map<String, String> map2 = new LinkedHashMap<>();
        map.put("name", "fkp");
        map2.put("name", "fkp2");
        map.putAll(map2);
        System.out.println(map);
    }
}