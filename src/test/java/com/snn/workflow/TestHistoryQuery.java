//package com.snn.workflow;
//
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.history.HistoricActivityInstance;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.history.HistoricVariableInstance;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
///**
// * @className TestHistoryQuery
// * @Author SNN
// * @Date 2019/9/22 20:28
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestHistoryQuery {
//
//    @Autowired
//    private HistoryService historyService;
//
//    // 查询历史流程实例
//    @Test
//    public void historyProcessInstaance() {
//        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
//                //条件
////        .processDefinitionId(processDefinitionId)
//        // .........
//                //排序
////        .orderByProcessDefinitionId()
//        //.........
//                //结果集
//        .list();
////        .listPage(firstResult, MaxResult)
////        .count()
////        .singleResult()
//        if(list != null && list.size()>0) {
//            for (HistoricProcessInstance hpi : list) {
//                System.out.println(hpi.getId());
//                System.out.println(hpi.getName());
//                System.out.println(hpi.getBusinessKey());
//                System.out.println(hpi.getDeploymentId());
//                System.out.println(hpi.getProcessDefinitionKey());
//                System.out.println(hpi.getEndTime());
//                System.out.println("====================");
//                //....
//            }
//        }
//    }
//
//    // 查询历史活动
//    @Test
//    public void queryHistoryAct() {
//        // 根据活动Id查
//        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list();
//        if(list != null && list.size()>0) {
//            for (HistoricActivityInstance hai : list) {
//                System.out.println(hai.getId());
//                System.out.println(hai.getActivityId());
//                System.out.println(hai.getAssignee());
//                System.out.println(hai.getEndTime());
//                System.out.println("====================");
//                //....
//            }
//        }
//    }
//
//    // 查询历史任务
//    @Test
//    public void queryHistoryTask() {
//        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().executionId("65001").list();
//        if(list != null && list.size()>0) {
//            for (HistoricTaskInstance hti : list) {
//                System.out.println(hti.getId());
//                System.out.println(hti.getAssignee());
//                System.out.println(hti.getEndTime());
//                System.out.println(hti.getName());
//                System.out.println("====================");
//                //....
//            }
//        }
//    }
//
//    // 查询历史流程变量
//    @Test
//    public void queryHistoryProcessVaribles() {
//        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().list();
//        if(list != null && list.size()>0) {
//            for (HistoricVariableInstance hvi : list) {
//                System.out.println(hvi.getId());
//                System.out.println(hvi.getVariableName());
//                System.out.println(hvi.getValue());
//                System.out.println(hvi.getTaskId());
//                System.out.println(hvi.getVariableTypeName());
//                System.out.println("====================");
//                //....
//            }
//        }
//    }
//}
