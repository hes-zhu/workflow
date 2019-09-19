package com.snn.workflowdemo;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className WorkFlowDemoApplication
 * @author lulu
 * springBoot启动配置类
 */
@SpringBootApplication(exclude = {
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
@MapperScan("com.snn.workflowdemo.dao")
@ComponentScan({"com.snn.workflowdemo","org.activiti.rest"})
public class WorkFlowDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkFlowDemoApplication.class, args);
    }

}
