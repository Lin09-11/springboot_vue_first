package com.xiaolin.springboot.mapper;

import com.xiaolin.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用于数据库连接
 */
@Mapper
public interface UserMapper {
    /**
     * 查询全部用户
     * @return
     */
    @Select("select * from sys_user;")
    public List<User> findAll();

    /**
     * 实现用户的插入
     * @param user
     * @return
     */
    @Insert("insert into sys_user(username, password, nickname, email, phone, address) VALUES\n" +
            "    (#{username},#{password},#{nickname},#{email},#{phone},#{address});")
    Integer insert(User user);

    /**
     * 更新用户信息
     * @param user
     */
    Integer update(User user);

    /**
     *  @Param("id")中的”id“要与where id中的传参id形式要一致的
     * @param id
     * @return
     */
    @Delete("delete from sys_user where id=#{id}")
    Integer deleteById(@Param("id") Integer id);

    /**
     * SpringBoot实现分页查询的基本原理
     * concat:使用模糊查询
     *
     * @param pageNum
     * @param pageSize
     * @param email
     * @param address
     * @return
     */
    @Select("select * from sys_user where (username like concat('%',#{username},'%') and " +
            "concat('%',#{email},'%') and concat('%',#{address},'%') ) limit #{pageNum} ,#{pageSize};")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username, String email, String address);

    /**
     * 查看总条数
     * @return
     */
    @Select("select count(*) from sys_user where (username like concat('%',#{username},'%') and " +
            "concat('%',#{email},'%') and concat('%',#{address},'%')) ;")
    Integer selectTotal(String username,String email,String address);
}
