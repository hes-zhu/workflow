package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.TDict;

/**
 * @className IProjectTypeService
 * @Author SNN
 * @Date 2019/10/28 20:58
 **/
public interface IProjectTypeService {
    ServiceResponse<TDict> getAllData();

    ServiceResponse insertProject(TDict tDict);

    ServiceResponse updateProject(TDict tDict);

    ServiceResponse deleteProject(Integer id);
}
