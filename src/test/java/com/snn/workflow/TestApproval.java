//package com.snn.workflow;
//
//import org.activiti.engine.TaskService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @className TestApproval
// * @Author SNN
// * @Date 2019/9/23 21:28
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestApproval {
//
//    @Autowired
//    private TaskService taskService;
//
//    @Test
//    public void success() {
//        Map<String, Object> varables = new HashMap<>();
//        varables.put("outcome", true);
//        taskService.complete("45002", varables);
//        System.out.println("end");
//    }
//
//    @Test
//    public void falid() {
//        Map<String, Object> varables = new HashMap<>();
//        varables.put("outcome", true);
//        taskService.complete("47502", varables);
//        System.out.println("end");
//    }
//}
