package com.imooc.restful.controller;

import com.imooc.restful.entity.Person;
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

    /**
     * /restful/request/100
     * @return
     */
    @PostMapping("/request/{rid}")
    @ResponseBody
    public String doPostRequest(@PathVariable("rid") Integer id, Person person){
        System.out.println("name:"+person.getName()+",age:"+person.getAge());
        return "{\"message\":\"徐doPostRequest\",\"rid\":"+id+"}";
    }

    @PutMapping("/request")
    @ResponseBody
    public String doPutMapping(Person person){
        System.out.println("name:"+person.getName()+",age:"+person.getAge());
        return "{\"message\":\"徐PutMapping\"}";
    }

    @DeleteMapping("/request")
    @ResponseBody
    public String doDeleteMapping(){
        return "{\"message\":\"徐DeleteMapping\"}";
    }

}
