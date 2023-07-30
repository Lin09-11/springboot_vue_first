package com.xiaolin.springboot.service.impl;

import com.xiaolin.springboot.entity.User;
import com.xiaolin.springboot.mapper.UserMapper;
import com.xiaolin.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
