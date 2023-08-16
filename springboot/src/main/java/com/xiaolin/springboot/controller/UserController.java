package com.xiaolin.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaolin.springboot.common.Constants;
import com.xiaolin.springboot.common.Result;
import com.xiaolin.springboot.controller.dto.UserDTO;
import org.apache.poi.hssf.usermodel.HSSFRangeCopier;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.xiaolin.springboot.service.IUserService;
import com.xiaolin.springboot.entity.User;
import org.springframework.web.multipart.MultipartFile;


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

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //从数据库中查询出所有的数据
        List<User> list = userService.list();
        //通过工具类创建writer写出到磁盘中
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        //在内存操作，写到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("username","用户名");
//        writer.addHeaderAlias("password","密码");
//        writer.addHeaderAlias("nickname","昵称");
//        writer.addHeaderAlias("email","邮箱");
//        writer.addHeaderAlias("phone","电话");
//        writer.addHeaderAlias("address","地址");
//        writer.addHeaderAlias("createTime","创建时间");
//        writer.addHeaderAlias("avatarUrl","头像");

        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }


    /**
     * 导出接口
     */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //通过javabean的方式读取Excel内的对象，但是要求表头必须是中文，跟javabean的属性要对应起来
        List<User> users = reader.readAll(User.class);
//        System.out.println(users);
        //将数据导入数据库
        userService.saveBatch(users);
        return true;
    }

    /**
     * 登录接口
     * @RequestBody:将前端json转后端
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        //获取前端填写的数据
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        //判断用户是否填入为空
        //StrUtil:是hutool下的
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }
}

