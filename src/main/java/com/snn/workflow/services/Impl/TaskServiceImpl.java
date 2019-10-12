package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.ITaskService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.snn.workflow.common.Const.getPageData;

/**
 * @className TaskServiceImpl
 * @Author SNN
 * @Date 2019/9/22 4:42
 **/
@Service("iTaskService")
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public ServiceResponse getAllTask(Integer rowSize, Integer page) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().listPage(rowSize * (page - 1), rowSize);
        // List<Model> models = repositoryService.createModelQuery().listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createProcessDefinitionQuery().count();
        // long countModel = repositoryService.createModelQuery().count();

        List<Object> processDefinitionList = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions) {
            // System.out.println(models.get(0).getMetaInfo());
            Map<String, Object> processDefinitionMap = new HashMap<>();
            processDefinitionMap.put("id", processDefinition.getId());
            processDefinitionMap.put("name", processDefinition.getName());
            processDefinitionMap.put("key", processDefinition.getKey());
            processDefinitionMap.put("diagramResourceName", processDefinition.getDiagramResourceName());
            processDefinitionMap.put("deploymentId", processDefinition.getDeploymentId());
            processDefinitionMap.put("version", processDefinition.getVersion());
            processDefinitionMap.put("description", processDefinition.getDescription());
            // processDefinitionMap.put("description", models.get(i).getMetaInfo());
            processDefinitionMap.put("resourceName", processDefinition.getResourceName());
            processDefinitionList.add(processDefinitionMap);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        getPageData(rowSize, page, count, data);
        data.put("processDefinitionList", processDefinitionList);
        return ServiceResponse.createBySuccess("查询成功", data);
    }

    @Override
    public ServiceResponse startTask(String key) {
        if (StringUtils.isNotBlank(key)) {
            runtimeService.startProcessInstanceByKey(key);
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    public ServiceResponse checkTask(String username) {
        if (StringUtils.isNotBlank(username)) {
            String assigned = username;
            List<Task> lists = taskService.createTaskQuery().taskAssignee(assigned).list();

            List<Object> taskList = new ArrayList<>();
            if (null != lists && lists.size() > 0) {
                for (Task task : lists) {
                    Map<String, Object> taskMap = new HashMap<>();
                    taskMap.put("id", task.getId());
                    taskMap.put("assignee", task.getAssignee());
                    taskMap.put("createTime", task.getCreateTime());
                    taskMap.put("name", task.getName());
                    taskMap.put("processDefinitionId", task.getProcessDefinitionId());
                    taskMap.put("processInstanceId", task.getProcessInstanceId());
                    taskMap.put("taskDefinitionKey", task.getTaskDefinitionKey());
                    taskMap.put("executionId", task.getExecutionId());
                    taskList.add(taskMap);
                }
                return ServiceResponse.createBySuccess("查询成功", taskList);
            }
        }
         return ServiceResponse.createByErrorMessage("查询成功，无待办任务");
//        return ServiceResponse.createBySuccessMessage("查询成功，无待办任务");
    }

    @Override
    public ServiceResponse completeTask(String taskId, Map<String, Object> map) {
        if (map.isEmpty()) {
            if (StringUtils.isNotBlank(taskId)) {
                taskService.complete(taskId);
                return ServiceResponse.createBySuccess();
            } else {
                return ServiceResponse.createByError();
            }
        }
        Map<String, Object> varables = new HashMap<>();
        if (map.get("money") != null) {
            Double money = (Double) map.get("money");
            varables.put("money", money);
        }
        if (map.get("tender") != null) {
            Boolean tender = (Boolean) map.get("tender");
            varables.put("tender", tender);
        }
        if (map.get("result") != null) {
            String result = (String) map.get("result");
            varables.put("result", result);
        }

        taskService.complete(taskId, varables);

        return ServiceResponse.createBySuccess();
    }

    @Override
    public ServiceResponse viewProcessImage() {
        // todo
        return null;
    }

    @Override
    public ServiceResponse getVarables(String executionId, String variable) {
        String result = (String) runtimeService.getVariable(executionId, variable);
        return ServiceResponse.createBySuccess(result);
    }

    @Override
    public ServiceResponse getTaskName(String executionId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().executionId(executionId).list();

        List<Object> taskNameList = new ArrayList<>();
        Map<String, Object> taskNameMap = new HashMap<>();
        if(list != null && list.size()>0) {
            int i = 0;
            for (HistoricTaskInstance hti : list) {
                taskNameMap.put("taskName"+i, hti.getName());
                i++;
            }
            taskNameList.add(taskNameMap);
            return ServiceResponse.createBySuccess(taskNameList);
        }
        return ServiceResponse.createByError();
    }
}