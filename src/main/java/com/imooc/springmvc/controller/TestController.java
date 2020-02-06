package com.imooc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试Controller
 */
@Controller
public class TestController {
    @GetMapping("/t")
    @ResponseBody
    public String test(String name) {
        return "SUCCESS111";
    }
}
