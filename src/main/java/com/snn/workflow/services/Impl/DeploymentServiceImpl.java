package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.services.IDeploymentService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.rest.service.api.repository.DeploymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.snn.workflow.common.Const.getPageData;


/**
 * @className DeploymentServiceImpl
 * @Author SNN
 * @Date 2019/9/23 20:54
 **/
@Service("iDeploymentService")
public class DeploymentServiceImpl implements IDeploymentService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public ServiceResponse getOneDeployed(String id) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(id).singleResult();
        if (deployment == null) {
            return ServiceResponse.createByErrorMessage("查询失败！");
        }
        DeploymentResponse deploymentResponse = new DeploymentResponse(deployment, "这好像是一个URL");
        return ServiceResponse.createBySuccess("查询成功", deploymentResponse);
    }

    @Override
    public ServiceResponse getAllDeployed(Integer rowSize, Integer page) {
        List<Deployment> deployments = repositoryService.createDeploymentQuery()
                .listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createDeploymentQuery().count();
        List<DeploymentResponse> deploymentList = new ArrayList<>();
        for(Deployment deployment: deployments){
            deploymentList.add(new DeploymentResponse(deployment, "这好像是一个URL"));
        }

        Map<String, Object> data = new HashMap<String, Object>();
        getPageData(rowSize, page, count, data);
        data.put("deploymentList", deploymentList);

        return ServiceResponse.createBySuccess("查询成功", data);
    }

    @Override
    public ServiceResponse deleteOneDeployed(String id) {
        // todo  检查该流程是否正在启动
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        if(list != null && list.size()>0) {
            for (ProcessInstance pi : list) {
                if (id.equals(pi.getDeploymentId())) {
                    return ServiceResponse.createByErrorMessage("删除失败，该流程还有任务没有执行完，请执行完该流程所有任务!");
                }
            }
        }
        repositoryService.deleteDeployment(id);
        return ServiceResponse.createBySuccessMessage("删除成功！");
    }
}
