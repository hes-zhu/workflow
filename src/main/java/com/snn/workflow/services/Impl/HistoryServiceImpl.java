//package com.snn.workflow.services.Impl;
//
//import com.snn.workflow.common.ServiceResponse;
//import com.snn.workflow.services.IHistoryService;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.history.HistoricActivityInstance;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.history.HistoricVariableInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @className HistoryServiceImpl
// * @Author SNN
// * @Date 2019/9/29 18:29
// **/
//public class HistoryServiceImpl implements IHistoryService {
//
//    @Autowired
//    private HistoryService historyService;
//
//    // 查询历史流程实例
//    @Override
//    public ServiceResponse historyProcessInstaance() {
//        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
//
//        List<Object> hisList = new ArrayList<>();
//        Map<String, Object> hisMap = new HashMap<>();
//
//        if(list != null && list.size()>0) {
//            for (HistoricProcessInstance hpi : list) {
//                hisMap.put("id", hpi.getId());
//                hisMap.put("name", hpi.getName());
//                hisMap.put("businessKey", hpi.getBusinessKey());
//                hisMap.put("deploymentId", hpi.getDeploymentId());
//                hisMap.put("processDefinitionKey", hpi.getProcessDefinitionKey());
//                hisMap.put("", hpi.getEndTime());
//                hisMap.put("", hpi.);
//                //....
//            }
//            return ServiceResponse.createBySuccess("查询成功", hisList);
//        }
//    }
//
//    // 查询历史活动
//    @Override
//    public ServiceResponse queryHistoryAct() {
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
//    @Override
//    public ServiceResponse queryHistoryTask() {
//        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
//        if(list != null && list.size()>0) {
//            for (HistoricTaskInstance hti : list) {
//                System.out.println(hti.getId());
//                System.out.println(hti.getAssignee());
//                System.out.println(hti.getEndTime());
//                System.out.println(hti.getStartTime());
//                System.out.println("====================");
//                //....
//            }
//        }
//    }
//
//    // 查询历史流程变量
//    @Override
//    public ServiceResponse queryHistoryProcessVaribles() {
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
