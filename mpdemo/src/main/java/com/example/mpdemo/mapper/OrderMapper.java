package com.example.mpdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpdemo.entity.Order;
import com.example.mpdemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from t_order where uid=#{uid}")
    List<Order> selectByUid(int id);

    //查询所有用户的订单信息
    @Select("select * from t_order")
    /**
     * colum:对应数据库中的字段名
     * property:对应类中的属性名
     */
    /**
     * 注意点:
     *  1.如果通过用户查询出order,表示订单可以有多条,则使用@Many
     *  2.如果通过order查询用户,表示用户只能有一个,则使用@One
     */
    @Results(
            {
                    @Result(column = "id", property = "id"),
                    @Result(column = "ordertime", property = "ordertime"),
                    @Result(column = "total", property = "total"),
                    @Result(column = "uid", property = "user", javaType = User.class,
                            many = @Many(select = "com.example.mpdemo.mapper.UserMapper.selectById")
                    )

            }
    )
    List<Order> selectAllOrdersAndUser();

}
