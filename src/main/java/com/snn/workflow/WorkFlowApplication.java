package com.snn.workflow;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className WorkFlowApplication
 * @author lulu
 * springBoot启动配置类
 */
@SpringBootApplication(exclude = {
        org.activiti.spring.boot.SecurityAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
@MapperScan("com.snn.workflow.dao")
@ComponentScan({"com.snn.workflow","org.activiti.rest"})
public class WorkFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkFlowApplication.class, args);
    }

}
