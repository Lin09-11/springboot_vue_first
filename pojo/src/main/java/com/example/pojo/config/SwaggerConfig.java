package com.example.pojo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//告诉Spring容器，这个类是一个配置类
@EnableSwagger2//启用Swagger2功能
public class SwaggerConfig {

    @Bean
    public Docket createResApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))//com包下的所有API都交给Swagger2管理
                .paths(PathSelectors.any()).build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("演示项目API")//标签
                .description("演示项目")//描述
                .version("1.0")//版本
                .build();
    }
}
