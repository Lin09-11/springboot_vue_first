package com.example.pojo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.synth.SynthTextAreaUI;

@RestController//使得可以向前端返回JSON对象
public class HelloController {
    //http://localhost:8080/hello
//    @GetMapping("/hello")// 与下面等价
    //http://localhost:8080/hello?nickname=zhangsan
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(String nickname){
        return "你好"+nickname;
    }
}
