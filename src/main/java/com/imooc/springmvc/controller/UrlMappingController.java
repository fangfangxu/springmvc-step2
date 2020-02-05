package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/um")
public class UrlMappingController {

    @GetMapping("/get")
    @ResponseBody
    public String post(User user){
        System.out.println(user.getUsername()+":"+user.getPassword());
        return "This is a post method!";
    }
}
