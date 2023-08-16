package com.xiaolin.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaolin.springboot.common.Constants;
import com.xiaolin.springboot.controller.dto.UserDTO;
import com.xiaolin.springboot.entity.User;
import com.xiaolin.springboot.exception.ServiceException;
import com.xiaolin.springboot.mapper.UserMapper;
import com.xiaolin.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import static org.springframework.boot.Banner.Mode.LOG;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小林
 * @since 2023-07-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO login(UserDTO userDTO) {
        //实现查询的对象封装操作类
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //对应数据库中的username和password的数据是否一致
        //queryWrapper.eq：表示等于
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        //这里只能接收一条数据，但是如果数据库中有”脏数据“【就是同样的数据有重复的】，前端登录时候会报错
         /**
        User one=getOne(queryWrapper);//最终得到实体类对象
        return one!=null;*/
         User one;
        //数据处理：使用抛出异常进行处理
        try {
            //从数据库查询用户信息
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        //判断用户是否输入信息
        if(one!=null){
            //将用户的信息复制，使得登录成功后可以在控制台中得到用户信息
            BeanUtil.copyProperties(one,userDTO,true);
            return userDTO;
        }else {
            //参考exception中的全局捕获异常
            throw new ServiceException(Constants.CODE_600,"用户名或者密码错误");
        }
    }
}
