package com.snn.workflow.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * @className ActivitiConfig
 * @Author lulu
 * @Date 2019-09-06 21:49
 * activiti的初始化配置
 **/
@Configuration
public class ActivitiConfig {

    /**
     * 流程配置，与spring整合采用SpringProcessEngineConfiguration这个实现
     */
    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(DruidConfig dataSource, PlatformTransactionManager transactionManager){
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource.druidDataSource());
        processEngineConfiguration.setDatabaseType("mysql");

        processEngineConfiguration.setTransactionManager(transactionManager);

        // 流程图字体
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");

        return processEngineConfiguration;
    }

    /**
     * 流程引擎，与spring整合使用factoryBean
     */
    @Bean
    public ProcessEngineFactoryBean processEngine(ProcessEngineConfiguration processEngineConfiguration){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        return processEngineFactoryBean;
    }

    /**
     * 八大接口
     * @param processEngine
     * @return
     */
    @Bean
    /**
     * @auther: SNN
     * @Description: 流程图的部署，修改，删除的服务： act_ge_property act_re_procdef act_re_deployment act_re_model
     * @MethodName: repositoryService
     * @return: org.activiti.engine.RepositoryService
     * @param: [processEngine]
     * @date: 2019/9/20 21:39
     **/
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }

    @Bean
    /**
     * @auther: SNN
     * @Description: 流程的运行服务：act_ru_variable act_ru_task act_ru_job act_ru_identitylink
     *                            act_ru_execution act_ru_event_subscr
     * @MethodName: runtimeService
     * @return: org.activiti.engine.RuntimeService
     * @param: [processEngine]
     * @date: 2019/9/20 21:40
     **/
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    @Bean
    /**
     * @auther: SNN
     * @Description: 与runtimeService一样
     * @MethodName: taskService
     * @return: org.activiti.engine.TaskService
     * @param: [processEngine]
     * @date: 2019/9/20 21:42
     **/
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    @Bean
    /**
     * @auther: SNN
     * @Description: 查询历史纪录的服务：act_hi_varinst act_hi_taskinst act_hi_procinst act_hi_identitylink
     *                               act_hi_detail act_hi_comment act_hi_attachment act_hi_actinst
     * @MethodName: historyService
     * @return: org.activiti.engine.HistoryService
     * @param: [processEngine]
     * @date: 2019/9/20 21:43
     **/
    public HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }

    @Bean
    /**
     * @auther: SNN
     * @Description: 页面表单服务
     * @MethodName: formService
     * @return: org.activiti.engine.FormService
     * @param: [processEngine]
     * @date: 2019/9/20 21:44
     **/
    public FormService formService(ProcessEngine processEngine){
        return processEngine.getFormService();
    }

    @Bean
    /**
     * @auther: SNN
     * @Description: 对工作流用户管理的表的操作：act_id_user act_id_membership act_id_info act_id_group
     * @MethodName: identityService
     * @return: org.activiti.engine.IdentityService
     * @param: [processEngine]
     * @date: 2019/9/20 21:45
     **/
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    @Bean
    /**
     * @auther: SNN
     * @Description: 管理器
     * @MethodName: managementService
     * @return: org.activiti.engine.ManagementService
     * @param: [processEngine]
     * @date: 2019/9/20 21:45
     **/
    public ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
        return processEngine.getDynamicBpmnService();
    }
    //八大接口 end

    /**
     * ObjectMapper
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
