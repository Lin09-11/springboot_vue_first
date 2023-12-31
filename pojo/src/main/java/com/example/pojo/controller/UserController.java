package com.example.pojo.controller;

import com.example.pojo.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id) {
        System.out.println(id);
        return "根据ID获取用户信息";
    }

    @PostMapping("/user")
    public String save(User user) {
        return "添加用户";
    }

    @PutMapping("/user")
    public String update(User user) {
        return "更新用户";
    }

    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable Integer id){
        System.out.println(id);
        return "根据ID删除用户";
    }
}
