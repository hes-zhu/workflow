package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.WxProjectItem;

/**
 * @className IProjectService
 * @Author SNN
 * @Date 2019/10/15 22:10
 **/
public interface IProjectService {

    ServiceResponse insertProject(WxProjectItem wxProjectItem);

    ServiceResponse updateProject(WxProjectItem wxProjectItem, String processDefId);

    ServiceResponse getProjectByProInsId(String ProcessInstanceId);
}
