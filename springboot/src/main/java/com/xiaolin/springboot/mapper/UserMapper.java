package com.xiaolin.springboot.mapper;

import com.xiaolin.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小林
 * @since 2023-07-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
