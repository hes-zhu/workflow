package com.snn.workflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @className WebMvcConfig
 * @Author SNN
 * @Date 2019/9/19 20:42
 * 跨域配置
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "HEAD")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
