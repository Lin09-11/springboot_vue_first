package com.example.pojo.config;

import com.example.pojo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {

    /**
     *  addPathPatterns方法定义拦截的地址
     *  excludePathPatterns定义排除某些地址不被拦截
     *  添加的一个拦截器没有addPathPattern任何一个url则默认拦截所有请求
     *  如果没有excludePathPatterns任何一个请求，则默认不放过任何一个请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor());//拦截全部路径
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");//拦截user下的路径
    }
}
