package com.xiaolin.springboot.controller;

import com.xiaolin.springboot.entity.User;
import com.xiaolin.springboot.mapper.UserMapper;
import com.xiaolin.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")//将该文件要进行访问时候，统一地址为“user”
public class UserController {

    @Autowired//导入UserMapper的实例对象
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping()
    /**
     * 新增和修改
     * @RequestBody:是将用户传入的数据转换为json对象
     */
    public Integer save(@RequestBody User user){
//        return userMapper.insert(user);
        return userService.save(user);
    }


    /**
     * 查询全部信息
     * @return
     */
    @GetMapping("/")
    public List<User> index(){
        return userMapper.findAll();
    }

    /**
     * 根据传入的id删除信息
     * @PathVariable:是获取用户输入的数据
     */
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }


    //分页查询
    //接口路径 ：/user/page?pageNum=1&pageSize=10
    /**
     * @RequestParam:是将输入的数据分别进行映射【pageNum=1 pageSize=10】
     * @param pageNum
     * @param pageSize
     * @return
     * limit第一个参数=（pageNum-1） * pageSize
     * limit第二个参数
     */
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username,
                                        @RequestParam String email,
                                        @RequestParam String address){
        pageNum=(pageNum-1)*pageSize;
        //data是渲染出来的数据
        List<User> data = userMapper.selectPage(pageNum, pageSize,username,email,address);
        //查询总条数
        Integer total = userMapper.selectTotal(username,email,address);
        //封装成一个Map对象
        Map<String,Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

}
