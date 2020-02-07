package com.imooc.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restful")
public class RestfulController {

    @GetMapping("/request")
    @ResponseBody
    public String doGetRequest(){
        return "{\"message\":\"徐doGetRequest\"}";
    }

    @PostMapping("/request")
    @ResponseBody
    public String doPostRequest(){
        return "{\"message\":\"徐doPostRequest\"}";
    }

    @PutMapping("/request")
    @ResponseBody
    public String doPutMapping(){
        return "{\"message\":\"徐PutMapping\"}";
    }

    @DeleteMapping("/request")
    @ResponseBody
    public String doDeleteMapping(){
        return "{\"message\":\"徐DeleteMapping\"}";
    }

}
