package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/um")
public class UrlMappingController {

    @PostMapping("/login")
    @ResponseBody
    public String login(User user) {
        String username = user.getUsername();
        String password = user.getPassword().toString();
        return "<fieldset><legend>登录成功</legend>用户名:" + username + "<br>密码：" + password + "</fieldset>";

    }
}
