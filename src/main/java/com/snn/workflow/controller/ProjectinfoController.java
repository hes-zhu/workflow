package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Projectinfo;
import com.snn.workflow.entity.WxProjectItem;
import com.snn.workflow.services.IProjectService;
import com.snn.workflow.services.IProjectinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className projectController
 * @Author SNN
 * @Date 2019/11/20 22:09
 **/
@Api("项目管理接口")
@RestController
@RequestMapping("api/projectinfo")
public class ProjectinfoController {
    @Autowired
    private IProjectinfoService iProjectinfoService;

    @ApiOperation("创建一个新项目")
    @PostMapping
    public ServiceResponse createProject(Projectinfo projectinfo) {
        return iProjectinfoService.insertProject(projectinfo);
    }

    @ApiOperation("根据流程实例ID更新项目")
    @PutMapping
    public ServiceResponse updateProject(Projectinfo projectinfo) {
        return iProjectinfoService.updateProject(projectinfo);
    }

    @ApiOperation("根据流程实例ID查询项目")
    @GetMapping("/{ProcessInstanceId}")
    public ServiceResponse<Projectinfo> getProjectByProInsId(@PathVariable("ProcessInstanceId")String ProcessInstanceId) {
        return iProjectinfoService.getProjectByProInsId(ProcessInstanceId);
    }
}
