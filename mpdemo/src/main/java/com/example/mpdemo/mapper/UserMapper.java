package com.example.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpdemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户
     *
     * @return
     */
//    @Select("select * from t_user")
//    public List<User> findAll();


//    @Insert("insert into t_user values (#{id},#{username},#{password},#{birthday})")
//    public int insert(User user);


//    @Delete("delete from t_user where id=#{id}")
//    public int delete(int id);

    /**
     * 查找id值的用户
     *
     * @param id
     * @return
     */
//    @Select("select * from t_user where id=#{id}")
//    public List<User> find(int id);
    @Select("select * from t_user where id = #{id}")
    User selectById(int id);

    //查询所有用户的订单信息
    @Select("select * from t_user")
    /**
     * colum:对应数据库中的字段名
     * property:对应类中的属性名
     */
    @Results(
            {
                    @Result(column = "id", property = "id"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "birthday", property = "birthday"),
                    @Result(column = "id", property = "orders", javaType = List.class,
                            many = @Many(select = "com.example.mpdemo.mapper.OrderMapper.selectByUid")
                    )

            }
    )
    List<User> selectAllUserAndOrders();
}
