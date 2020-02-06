package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.Form;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FormController {
    /**
     * Integer[] purpose
     * @RequestParam List<Integer> 用集合接收复合参数需要加上 @RequestParam
     * @param name
     * @param course
     * @param purpose
     * @return
     */
    @PostMapping("/apply1")
    @ResponseBody
    public String apply(@RequestParam(value="n",defaultValue = "nn") String name, String course, @RequestParam List<Integer> purpose){
        return name+course+purpose;
    }

    @PostMapping("/apply")
    @ResponseBody
    public String apply(Form form){
        return form.getName()+form.getCourse()+form.getPurpose().get(0);
    }
}
