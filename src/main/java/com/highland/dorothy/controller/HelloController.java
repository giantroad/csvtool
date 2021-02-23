package com.highland.dorothy.controller;

import com.highland.dorothy.tool.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("index")
    public String hello(){

        return "<font style='font-size:28px;'>Hello Spring Boot</font>";
    }
}