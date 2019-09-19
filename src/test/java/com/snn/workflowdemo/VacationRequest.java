package com.snn.workflowdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.rest.editor.model.ModelSaveRestResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @className VacationRequest
 * @Author lulu
 * @Date 2019-09-10 10:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class VacationRequest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void demo() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", 4);
        variables.put("vacationMotivation", "I'm really tired!");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process", variables);

        // Verify that we started a new process instance
        LOGGER.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
        System.out.println("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());

    }
}
