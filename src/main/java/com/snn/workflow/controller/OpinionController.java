package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Opinion;
import com.snn.workflow.services.IOpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className OpinionController
 * @Author lulu
 * @Date 2019/11/8 下午10:56
 **/
@Api("意见控制器")
@RestController
@RequestMapping("/api/opinion")
public class OpinionController {

    @Autowired
    private IOpinionService opinionService;

    @ApiOperation("插入一条意见")
    @PostMapping
    public ServiceResponse insertOne(@ApiParam("一个opinion对象") Opinion opinion) {
        return opinionService.insert(opinion);
    }

    @ApiOperation("查询一条意见")
    @GetMapping
    public ServiceResponse getOne(@RequestParam("projectId") @ApiParam("projectId") String projectId) {
        return opinionService.getOpinionById(Integer.parseInt(projectId));
    }
}
