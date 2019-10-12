package com.snn.workflow.controller;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.IDeploymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className DeploymentController
 * @Author lulu
 * @Date 2019-09-10 14:08
 **/
@Api("模型部署管理接口")
@RestController
@RequestMapping("api/deployments")
public class DeploymentController {

    @Autowired
    private IDeploymentService iDeploymentService;

    @ApiOperation("根据ID查询具体部署实例")
    @GetMapping("{id}")
    /**
     * @auther: lulu
     * @Description: 根据ID查询具体部署实例
     * @MethodName: getOneDeployed
     * @return: java.lang.Object
     * @param: [id]
     * @date: 2019-09-10 14:19
     **/
    public ServiceResponse getOneDeployed(@ApiParam("部署Id") @PathVariable("id") String id) {
        return iDeploymentService.getOneDeployed(id);
    }

    @ApiOperation("查询所有部署实例")
    @GetMapping
    /**
     * @auther: lulu
     * @Description: 查询所有部署实例
     * @MethodName: getAllDeployed
     * @return: java.lang.Object
     * @param: [rowSize, page]
     * @date: 2019-09-10 14:19
     **/
    public ServiceResponse getAllDeployed(@ApiParam("每一页数据长度") @RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @ApiParam("将数据分成多少页") @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        return iDeploymentService.getAllDeployed(rowSize, page);
    }

    @ApiOperation("根据部署Id删除部署实例")
    @DeleteMapping("{id}")
    /**
     * @auther: lulu
     * @Description: 删除部署实例
     * @MethodName: deleteOneDeployed
     * @return: java.lang.Object
     * @param: [id]
     * @date: 2019-09-10 14:19
     **/
    public ServiceResponse deleteOneDeployed(@ApiParam("部署Id") @PathVariable("id") String id) {
        return iDeploymentService.deleteOneDeployed(id);
    }
}
