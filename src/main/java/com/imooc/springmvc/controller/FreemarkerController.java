package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreemarkerController {
    @GetMapping("/maker")
    public ModelAndView showFtl(){
        ModelAndView mav=new ModelAndView("/marker");
        User u=new User();
        u.setUsername("徐xiaofang");
        mav.addObject("u",u);
        return mav;
    }
}
