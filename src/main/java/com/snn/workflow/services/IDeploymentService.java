package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;

/**
 * @className IDeploymentService
 * @Author SNN
 * @Date 2019/9/23 20:56
 **/
public interface IDeploymentService {
    ServiceResponse getOneDeployed(String id);

    ServiceResponse getAllDeployed(Integer rowSize, Integer page);

    ServiceResponse deleteOneDeployed(String id);
}
