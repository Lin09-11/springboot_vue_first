package com.xiaolin.springboot.service;

import com.xiaolin.springboot.entity.User;
import com.xiaolin.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//将实例对象交给IOC容器
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int save(User user){
        if(user.getId()==null){//user没有id，则表示新增加
           return userMapper.insert(user);
        }else {//user有id，表示更新
            return userMapper.update(user);
        }
    }
}
