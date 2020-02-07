package com.imooc.restful.controller;

import com.imooc.restful.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/restful")
//@CrossOrigin(origins = {"http://localhost"},maxAge = 3600)
public class RestfulController {

    @GetMapping("/request")
//    @ResponseBody
    public String doGetRequest(){
        return "{\"message\":\"徐doGetRequest\"}";
    }

    /**
     * /restful/request/100
     * @return
     */
    @PostMapping("/request/{rid}")
//    @ResponseBody
    public String doPostRequest(@PathVariable("rid") Integer id, Person person){
        System.out.println("name:"+person.getName()+",age:"+person.getAge());
        return "{\"message\":\"徐doPostRequest\",\"rid\":"+id+"}";
    }

    @PutMapping("/request")
//    @ResponseBody
    public String doPutMapping(Person person){
        System.out.println("name:"+person.getName()+",age:"+person.getAge());
        return "{\"message\":\"徐PutMapping\"}";
    }

    @DeleteMapping("/request")
//    @ResponseBody
    public String doDeleteMapping(){
        return "{\"message\":\"徐DeleteMapping\"}";
    }


    @GetMapping("/person")
    public Person findPerson(){
        Person person=new Person();
        person.setName("wang宇");
        person.setAge(10);
        return person;
    }

    @GetMapping("/persons")
    public List<Person> findPersons(){
        List<Person> list=new ArrayList<Person>();
        Person person1=new Person();
        person1.setName("wang宇");
        person1.setAge(10);
        person1.setDate(new Date());
        Person person2=new Person();
        person2.setName("xuf");
        person2.setAge(10);
        person2.setDate(new Date());
        list.add(person2);
        list.add(person1);
        return list;
    }

}
