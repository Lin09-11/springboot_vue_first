package com.xiaolin.springboot.controller.dto;
import lombok.Data;

/**
 * 用于接收前端登录请求的参数
 */

@Data
public class UserDTO {
    private String username;
    private String password;
    //用户登录成功后显示的名称
    private String nickname;
    //用户登录成功后显示的用户头像
    private String avatarUrl;
}
