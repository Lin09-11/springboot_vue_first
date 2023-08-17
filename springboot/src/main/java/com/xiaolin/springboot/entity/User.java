package com.xiaolin.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author 小林
 * @since 2023-07-30
 */
@Data
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * ID
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户名
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 昵称
     */
      private String nickname;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 手机号
     */
      private String phone;

      /**
     * 地址
     */
      private String address;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      /**
     * 图片链接
     */
      private String avatarUrl;


}
