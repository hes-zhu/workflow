package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.TDict;
import com.snn.workflow.entity.WxProjectItem;
import com.snn.workflow.services.IProjectService;
import com.snn.workflow.services.IProjectTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className ProjectTypeController
 * @Author SNN
 * @Date 2019/10/28 20:58
 **/
@Api("项目分类管理接口")
@RestController
@RequestMapping("api/projectType")
public class ProjectTypeController {
    @Autowired
    private IProjectTypeService iProjectTypeService;

    @ApiOperation("获取项目分类PID=3")
    @GetMapping
    public ServiceResponse getAllProjectType() {
        return iProjectTypeService.getAllData();
    }

    @ApiOperation("根据ID更新项目")
    @PutMapping
    public ServiceResponse updateProject(TDict tDict) {
        return iProjectTypeService.updateProject(tDict);
    }

    @ApiOperation("根据ID删除项目")
    @DeleteMapping("/{Id}")
    public ServiceResponse DeleteProjectByPId(@PathVariable("Id")Integer id) {
        return iProjectTypeService.deleteProject(id);
    }

    @ApiOperation("增加项目")
    @PostMapping
    public ServiceResponse createProject(TDict tDict) {
        return iProjectTypeService.insertProject(tDict);
    }
}
