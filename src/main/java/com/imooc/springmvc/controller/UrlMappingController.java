package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.Date;


@Controller
@RequestMapping("/um")
public class UrlMappingController {

    //    @PostMapping("/login")
    @ResponseBody
    public String login(User user, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        String username = user.getUsername();
        String password = user.getPassword().toString();
        return "<fieldset><legend>登录成功</legend>用户名:" + username + "<br>密码：" + password + "</fieldset>";

    }

    @PostMapping("/login")
    @ResponseBody
    public String login2(User user, Date date) {
        String username = user.getUsername();
        String password = user.getPassword().toString();
        return "<fieldset><legend>登录成功</legend>用户名:" + username + "<br>密码：" + password + "</fieldset>";
    }

    @GetMapping("/view")
    public ModelAndView showView(Integer id) {
        //  "/"此处是从应用的根路径进行访问
        ModelAndView mav = new ModelAndView("/view.jsp");
        User user = new User();
        if (id == 1) {
            user.setUsername("wangyx");
        } else {
            user.setUsername("xuff");
        }
        mav.addObject("u", user);
        return mav;
    }
}
