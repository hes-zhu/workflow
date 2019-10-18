package com.snn.workflow;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.WxProjectItem;
import com.snn.workflow.services.IProjectService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @className TestProject
 * @Author SNN
 * @Date 2019/10/15 22:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProject {
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void test1() {
        WxProjectItem wxProjectItem = new WxProjectItem();
        wxProjectItem.setProjectbudget(100000.0f);
        wxProjectItem.setProjectname("测试项目");
        wxProjectItem.setProjecttype("测试类别");
        System.out.println(iProjectService.insertProject(wxProjectItem));
    }

    @Test
    public void test02() {
        ProcessDefinition list = repositoryService.getProcessDefinition("SecondProcess:5:95031");
        System.out.println(list);
        System.out.println(list.getId());
        System.out.println(list.getKey());
        System.out.println(list.getName());
        System.out.println(list.getDescription());
        System.out.println(list.getDeploymentId());
        System.out.println(list.getVersion());
        System.out.println(list.getClass());
        System.out.println(list.getTenantId());
        System.out.println(list.getDiagramResourceName());
        System.out.println(list.getCategory());
    }

    @Test
    public void test03() {
        String processInstanceId = "150026";
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if (tasks != null && tasks.size() != 0){
            for (Task task : tasks) {
                ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
                List<ActivityImpl> activitiList = def.getActivities();
                String excId = task.getExecutionId();
                ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
                String activitiId = execution.getActivityId();
                for(ActivityImpl activityImpl : activitiList){
                    String id = activityImpl.getId();
                    if(activitiId.equals(id)){
                        System.out.println("当前任务："+activityImpl.getProperty("name")); //输出某个节点的某种属性
                        List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
                        for(PvmTransition tr:outTransitions){
                            PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                            System.out.println("下一步任务任务："+ac.getProperty("name"));
                        }
                        break;
                    }
                }
            }
        }

    }

}
