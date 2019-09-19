package com.snn.workflowdemo.controller;

import com.snn.workflowdemo.common.ServiceResponse;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.rest.service.api.repository.DeploymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className DeploymentController
 * @Author lulu
 * @Date 2019-09-10 14:08
 **/
@RestController
@RequestMapping("deployments")
public class DeploymentController {

    @Autowired
    RepositoryService repositoryService;

    @GetMapping("{id}")
    /**
     * @auther: lulu
     * @Description: 根据ID查询具体部署实例
     * @MethodName: getOne
     * @return: java.lang.Object
     * @param: [id]
     * @date: 2019-09-10 14:19
     **/
    public Object getOne(@PathVariable("id") String id) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(id).singleResult();
        if (deployment == null) {
            return ServiceResponse.createByErrorMessage("查询失败！");
        }
        DeploymentResponse deploymentResponse = new DeploymentResponse(deployment, "这好像是一个URL");
        return ServiceResponse.createBySuccess("查询成功", deploymentResponse);
    }

    @GetMapping
    /**
     * @auther: lulu
     * @Description: 查询所有部署实例
     * @MethodName: getList
     * @return: java.lang.Object
     * @param: [rowSize, page]
     * @date: 2019-09-10 14:19
     **/
    public Object getList(@RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize, @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        List<Deployment> deployments = repositoryService.createDeploymentQuery()
                .listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createDeploymentQuery().count();
        List<DeploymentResponse> list = new ArrayList<>();
        for(Deployment deployment: deployments){
            list.add(new DeploymentResponse(deployment, "这好像是一个URL"));
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("TotalRows", count);
        data.put("RowSize", rowSize);
        data.put("TotalPages", (int) (count/rowSize+1));
        data.put("CurrentPage", page);
        data.put("list", list);

        return ServiceResponse.createBySuccess("查询成功", data);
    }

    @DeleteMapping("{id}")
    /**
     * @auther: lulu
     * @Description: 删除部署实例
     * @MethodName: deleteOne
     * @return: java.lang.Object
     * @param: [id]
     * @date: 2019-09-10 14:19
     **/
    public Object deleteOne(@PathVariable("id") String id) {
        repositoryService.deleteDeployment(id);
        return ServiceResponse.createBySuccessMessage("删除成功！");
    }
}
