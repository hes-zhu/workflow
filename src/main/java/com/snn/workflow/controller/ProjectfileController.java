package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.WxProjectItem;
import com.snn.workflow.services.IFileService;
import com.snn.workflow.services.IProjectService;
import com.snn.workflow.services.IProjectfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @className projectController
 * @Author SNN
 * @Date 2019/10/15 22:09
 **/
@Api("项目文件管理接口")
@RestController
@RequestMapping("api/projectfile")
public class ProjectfileController {

    @Autowired
    private IProjectfileService projectfileService;
    @Autowired
    private IFileService fileService;

    @GetMapping("{projectId}")
    public ServiceResponse showFile(@PathVariable("projectId") String projectId) {
        return projectfileService.showFile(Integer.parseInt(projectId));
    }

    @PostMapping("upload")
    public ServiceResponse upload(@RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) {
        String fileUrl = fileService.upload(file);
        return projectfileService.upload(fileUrl, Integer.parseInt(projectId));
    }

    @PutMapping("{id}")
    public ServiceResponse updateFileState(@PathVariable("id") String id) {
        return projectfileService.updateFileState(Integer.parseInt(id));
    }
}
