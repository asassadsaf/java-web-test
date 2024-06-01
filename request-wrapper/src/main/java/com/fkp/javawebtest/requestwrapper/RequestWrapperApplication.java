package com.fkp.javawebtest.requestwrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.fkp.javawebtest.requestwrapper.config")
public class RequestWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestWrapperApplication.class, args);
        //test
        //test github to gitee 1 2
    }

}
