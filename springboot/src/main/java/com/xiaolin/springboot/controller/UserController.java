package com.xiaolin.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.xiaolin.springboot.service.IUserService;
import com.xiaolin.springboot.entity.User;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小林
 * @since 2023-07-30
 */
@RestController
@RequestMapping("/user")
        public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public Boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
        }

    //使用Mybatis-plus，进行的是删除单个数据
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    //使用Mybatis-plus，进行批量删除
    @DeleteMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer>  ids){//此时ids是一个数组
        return userService.removeBatchByIds(ids);
    }

    @GetMapping("/{id}")
    public List<User> findOne(@PathVariable Integer id) {
        return userService.list();
        }

    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String nickname,
                                @RequestParam(defaultValue = "") String address
    )
    {
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /**
         * 有“or”则其他条件会失效
         */
        if(!"".equals(username)){//只有当不为空的时候才可以进行拼接
            queryWrapper.like("username",username);
        }
        if(!"".equals(nickname)){//只有当不为空的时候才可以进行拼接
            queryWrapper.like("nickname",nickname);
        }
        if(!"".equals(address)){//只有当不为空的时候才可以进行拼接
            queryWrapper.like("address",address);
        }
        //将数据进行倒叙展示
        queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }
    }

