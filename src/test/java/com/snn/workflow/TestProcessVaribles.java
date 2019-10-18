package com.snn.workflow;

import com.snn.workflow.entity.User;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className VacationTest1
 * @Author SNN
 * @Date 2019/9/22 19:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProcessVaribles {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    // 启动任务
    @Test
    public void vacation() {
//        String key = "SecondProcess";
        // 没有流程变量的启动流程
        // runtimeService.startProcessInstanceById("52507");
//        runtimeService.startProcessInstanceByKey("请假流程");

        // 启动流程并创建流程变量
        Map<String, Object> map = new HashMap<>();
//        map.put("type", true);
        map.put("agree", false);
//        map.put("money", 1000000);
//        map.put("result", "开始启动流程图");
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
//        System.out.println("流程启动成功"+processInstance.getId());
    }

    // 办理任务
    @Test
    public void completeTask() {
        // 根据任务id完成任务
        String TaskId1 = "142502";

        Map<String, Object> map = new HashMap<>();
//        map.put("type", true);
//        map.put("money", 600000);
//        map.put("result", "填写项目申请");
//        map.put("result", "材料审核");
//        map.put("result", "院长审核");
        map.put("agree", true);

        // 根据任务id完成任务并指定流程变量
        taskService.complete(TaskId1, map);
        System.out.println("任务办理完成");
//        taskService.complete();
    }

        // 查询任务
    @Test
    public void findTask() {
        String assigned1 = "pd-operator";
        String assigned2 = "st-operator";
//        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(assigned1).list();
        List<Task> list = taskService.createTaskQuery()
                 /*条件*/
            .taskAssignee(assigned2)  //根据任务办理人查询任务
                /*排序*/
            .orderByTaskCreateTime().desc()
            //..........
                /*结果集*/
            .list();
        if(null != list && list.size()>0) {
            for (Task task : list) {
                System.out.println(task.getId());
                System.out.println(task.getName());
                System.out.println(task.getAssignee());
                System.out.println(task.getCategory());
                System.out.println(task.getOwner());
                System.out.println(task.getProcessInstanceId());
                System.out.println(task.getProcessDefinitionId());
                System.out.println(task.getExecutionId());
                System.out.println("查询成功");
            }
        } else {
            System.out.println("查询失败");
        }
    }

    // 设置流程变量
    @Test
    public void setVaribles() {
        String executionId = "20001";
//        runtimeService.setVariable(executionId, "请假人", "小明");
        Map<String, Object> map = new HashMap<>();
        map.put("请假天数", 5);
        map.put("请假时间", new Date());
        map.put("请假原因", "约会");
        map.put("用户对象", new User());
        runtimeService.setVariables(executionId, map);
        System.out.println("流程变量设置成功");

        // TaskService也能设置流程变量
    }

    // 获取流程变量
    @Test
    public void getVaribles() {
        String executionId = "135001";
        boolean reason = (boolean) runtimeService.getVariable(executionId, "agree");
        System.out.println(reason);
    }

    // 查询历史流程变量
    @Test
    public void queryHistoryProcessVaribles() {
        String processInstanceId = "20001";
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        if(list != null && list.size()>0) {
            for (HistoricVariableInstance hvi : list) {
                System.out.println(hvi.getId());
                System.out.println(hvi.getVariableName());
                System.out.println(hvi.getValue());
                System.out.println(hvi.getTaskId());
                System.out.println(hvi.getVariableTypeName());
                System.out.println("====================");
                //....
            }
        }
    }

}































