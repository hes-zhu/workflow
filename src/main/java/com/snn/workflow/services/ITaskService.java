package com.snn.workflow.services;


import com.snn.workflow.common.ServiceResponse;

import java.util.Map;

/**
 * @className ITaskService
 * @Author lulu
 * @Date 2019-09-22 04:42
 **/
public interface ITaskService {

    ServiceResponse getAllTask(Integer rowSize, Integer page);

    ServiceResponse startTask(String key);

    ServiceResponse deleteProcessInstance(String processInstanceID, String reason);

    ServiceResponse checkTask(String username);

    ServiceResponse completeTask(String taskId, Map<String, Object> map);

    ServiceResponse viewProcessImage();

    ServiceResponse getVarables(String executionId, String variable);

    ServiceResponse getTaskName(String executionId);
}
