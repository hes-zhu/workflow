package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.WxProjectItem;
import com.snn.workflow.services.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className projectController
 * @Author SNN
 * @Date 2019/10/15 22:09
 **/
@Api("项目管理接口")
@RestController
@RequestMapping("api/project")
public class ProjectController {
    @Autowired
    private IProjectService iProjectService;

    @ApiOperation("创建一个新项目")
    @PostMapping("/createProject")
    public ServiceResponse createProject(WxProjectItem wxProjectItem) {
        return iProjectService.insertProject(wxProjectItem);
    }

    @ApiOperation("根据流程实例ID更新项目")
    @PutMapping("/{ProcessInstanceId}")
    public ServiceResponse updateProject(WxProjectItem wxProjectItem, @PathVariable("ProcessInstanceId")String ProcessInstanceId) {
        return iProjectService.updateProject(wxProjectItem, ProcessInstanceId);
    }

    @ApiOperation("根据流程实例ID查询项目")
    @GetMapping("/{ProcessInstanceId}")
    public ServiceResponse getProjectByProInsId(@PathVariable("ProcessInstanceId")String ProcessInstanceId) {
        return iProjectService.getProjectByProInsId(ProcessInstanceId);
    }
}
