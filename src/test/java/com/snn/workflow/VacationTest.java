//package com.snn.workflow;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.activiti.bpmn.converter.BpmnXMLConverter;
//import org.activiti.bpmn.model.BpmnModel;
//import org.activiti.editor.language.json.converter.BpmnJsonConverter;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.repository.Model;
//import org.activiti.engine.repository.ProcessDefinition;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @className VacationRequest
// * @Author lulu
// * @Date 2019-09-10 10:44
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class VacationTest {
//
//    protected static final Logger LOGGER = LoggerFactory.getLogger(VacationTest.class);
//
//    @Autowired
//    private RepositoryService repositoryService;
//    @Autowired
//    private RuntimeService runtimeService;
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private HistoryService historyService;
//
//    // 部署流程
//    @Test
//    public void deployed() throws IOException {
//        String id = "52501";
//        //获取模型
//        Model modelData = repositoryService.getModel(id);
//        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
//
//        if (bytes == null) {
//            System.out.println("模型数据为空，请先设计流程并成功保存，再进行发布。");
//        }
//
//        JsonNode modelNode = new ObjectMapper().readTree(bytes);
//
//        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
//        if(model.getProcesses().size() == 0){
//            System.out.println("数据模型不符要求，请至少设计一条主线流程。");
//        }
//        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
//
//        // 发布流程
//        String processName = modelData.getName() + ".bpmn20.xml";
//        Deployment deployment = repositoryService.createDeployment()
//                .name(modelData.getName())
//                .addString(processName, new String(bpmnBytes, "UTF-8"))
//                .deploy();
//        modelData.setDeploymentId(deployment.getId());
//        repositoryService.saveModel(modelData);
//        System.out.println("流程部署成功，流程id为："+deployment.getId());
//    }
//
//    // 启动任务
//    @Test
//    public void vacation() {
//        String key = "请假流程";
//        // 没有流程变量的启动流程
//        // runtimeService.startProcessInstanceById("52507");
////        runtimeService.startProcessInstanceByKey("请假流程");
//
//        // 启动流程并创建流程变量
//        Map<String, Object> map = new HashMap<>();
//        map.put("请假天数", 5);
//        map.put("请假时间", new Date());
//        map.put("请假原因", "约会");
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
//        System.out.println("流程启动成功");
//    }
//
//    // 查询任务
//    @Test
//    public void findTask() {
//        String assigned1 = "张三";
//        String assigned2 = "李四";
//        String assigned3 = "王五";
//        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(assigned1).list();
//        String assigned = "";
//        taskService.createTaskQuery()
//                 /*条件*/
//            .taskAssignee(assigned)  //根据任务办理人查询任务
////            .deploymentId(deploymentId)  //根据部署ID查询where id = id
////            .deploymentIdIn(deploymentIds)  //根据部署ID集合查询where id in （1，2，3，4）
////            .executionId(executionId)  //根据执行id查询
////            .processDefinitionId(processDefinitionId)
////            .processDefinitionKey(processDefinitionKey)
////            .processDefinitionKeyIn(processDefinitionKeys)
////            .processDefinitionName(processDefinitionName)
////            .processDefinitionNameLike(processDefinitionName)
////            .processInstanceBusinessKey()
//               // .........
//                /*排序*/
//            .orderByTaskCreateTime().desc()
////          .orderByDueDateNullsFirst().desc()
////          .orderByProcessDefinitionId()
////          .orderByProcessInstanceId()
//            //..........
//                /*结果集*/
//            .list();
////            .listPage(firstResult, MaxResult)
////            .count()
////            .singleResult()
//        if(null != list && list.size()>0) {
//            for (Task task : list) {
//                System.out.println(task.getId());
//                System.out.println(task.getName());
//                System.out.println(task.getAssignee());
//                System.out.println(task.getCategory());
//                System.out.println(task.getOwner());
//                System.out.println(task.getProcessInstanceId());
//                System.out.println(task.getProcessDefinitionId());
//                System.out.println(task.getExecutionId());
//                System.out.println("查询成功");
//            }
//        }
//        System.out.println("查询失败");
//    }
//
//    // 办理任务
//    @Test
//    public void completeTask() {
//        // 根据任务id完成任务
//        String TaskId1 = "62504";
//        String TaskId2 = "65002";
//        String TaskId3 = "67502";
//        taskService.complete(TaskId1);
//        System.out.println("任务办理完成");
//        // 根据任务id完成任务并指定流程变量
////        taskService.complete();
//    }
//
//    //判断流程是否结束
//    //作用：更新业务表里面的状态
//    @Test
//    public void isComplete() {
//        // 已知流程实例ID
//        String processInstanceId = "";
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId(processInstanceId)
//                .singleResult();
//        if (processInstance != null) {
//            System.out.println("流程未结束");
//        } else {
//            System.out.println("流程结束");
//        }
//    }
//
//    //查询当前流程实例
//    @Test
//    public void queryProcessInstance() {
//        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
//        if(list != null && list.size()>0) {
//            for (ProcessInstance pi : list) {
//                System.out.println(pi.getDeploymentId());
//                System.out.println(pi.getProcessDefinitionId());
//                System.out.println(pi.getName());
//                //....
//            }
//        }
//    }
//
//    //查询历史任务
//    @Test
//    public void queryHistoryTask() {
//        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
//        if(list != null && list.size()>0) {
//            for (HistoricTaskInstance hti : list) {
//                System.out.println(hti.getStartTime());
//                System.out.println(hti.getEndTime());
//                System.out.println(hti.getName());
//                //....
//            }
//        }
//    }
//
//    //查询历史流程实例
//    @Test
//    public void queryHistoryProcessInstance() {
//        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
//        if(list != null && list.size()>0) {
//            for (HistoricProcessInstance hpi : list) {
//                System.out.println(hpi.getBusinessKey());
//                System.out.println(hpi.getName());
//                System.out.println(hpi.getDeleteReason());
//                //....
//            }
//        }
//    }
//
//    //查看流程图 根据流程定义ID
//    @Test
//    public void viewProcessImg() {
//        String processDefId = "first:1:60007";
//        InputStream inputStream = repositoryService.getProcessDiagram(processDefId);
//        File file = new File("C:\\Users\\SNN\\SNN\\Desktop\\All activiti\\workflow\\src\\main\\resources\\hello.png");
//        try {
//            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
//            int len = 0;
//            byte[] b = new byte[1024];
//            while ((len = inputStream.read(b)) != -1) {
//                outputStream.write(b, 0, len);
//                outputStream.flush();
//            }
//            outputStream.close();
//            inputStream.close();
//            System.out.println("图片写入成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //查看流程图 根据流程部署ID
//    @Test
//    public void viewProcessImgByDeployedId() {
//        String DeployedId = "4";
//        // 根据流程部署ID查询流程定义对象
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(DeployedId).singleResult();
//        // 根据流程定义对象里面查询出定义Id
//        String processDrfId = processDefinition.getId();
//        InputStream inputStream = repositoryService.getProcessDiagram(processDrfId);
//        System.out.println(processDefinition.getDiagramResourceName());
//        File file = new File("C:\\Users\\SNN\\SNN\\Desktop\\All activiti\\workflow\\src\\main\\resources\\"+processDefinition.getDiagramResourceName());
//        try {
//            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
//            int len = 0;
//            byte[] b = new byte[1024];
//            while ((len = inputStream.read(b)) != -1) {
//                outputStream.write(b, 0, len);
//                outputStream.flush();
//            }
//            outputStream.close();
//            inputStream.close();
//            System.out.println("图片写入成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void logTesy() {
//        LOGGER.error("这是我测试的错误消息");
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
