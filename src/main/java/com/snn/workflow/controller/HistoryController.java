package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.IHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HistoryController
 * @Author SNN
 * @Date 2019/9/29 18:28
 **/
@Api("历史流程资源管理接口")
@RestController
@RequestMapping("api/history")
public class HistoryController {

    @Autowired
    private IHistoryService iHistoryService;


    @ApiOperation("查询历史流程变量信息")
    @GetMapping("/processInstance")
    public ServiceResponse getProcessInstance() {
        return iHistoryService.historyProcessInstance();
    }



}
