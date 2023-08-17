package com.example.mpdemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpdemo.entity.User;
import com.example.mpdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin//解决前后端跨域请求
public class UserController {

    @Autowired//自动注入实例对象
    private UserMapper userMapper;

    @GetMapping("/user")
    public List queryAll() {//将返回值从"String"修改为"List"则自动转换为JSON格式
//        List<User> list = userMapper.findAll();
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    @PostMapping("/user")
    public String save(User user) {
//        int i = userMapper.insert(user);
        int i = userMapper.insert(user);
        if (i > 0) {
            return "插入成功";
        } else {
            return "插入失败";
        }
    }

    @DeleteMapping("/user/{id}")
    public String del(@PathVariable Integer id) {
//        int i = userMapper.delete(id);
        int i = userMapper.deleteById(id);
        if (i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @GetMapping("/user/{id}")
    public User query(@PathVariable Integer id) {
//         return userMapper.find(id);
        User user = userMapper.selectById(id);
        return user;
    }


    @GetMapping("/user/findAll")
    public List<User> find() {
        return userMapper.selectAllUserAndOrders();
    }

    //条件查询
    @GetMapping("/user/find")
    public List<User> findByCond() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "zhangsan");
        return userMapper.selectList(queryWrapper);
    }

    //分页查询
    @GetMapping("/user/findByPage")
    public IPage findByPage(){
        //设置起始值及每一页数
        //Page(起始条数,一页几条)
        Page<User> page = new Page<>(0, 2);
        IPage iPage=userMapper.selectPage(page,null);
        return iPage;
    }
}
