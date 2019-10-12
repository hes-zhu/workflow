package com.snn.workflow.config;

import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className Swagger2Config
 * @Author SNN
 * @Date 2019/9/23 18:32
 * 自动生成接口文档
 * 配置博客：https://www.cnblogs.com/suizhikuo/p/9397417.html
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("工作流信息管理系统_接口文档")
                        .description("用于前端接口调用")
//                        .contact(new Contact("噜噜", "http://localhost:8080", "123@qq.com"))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.snn.workflow.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
