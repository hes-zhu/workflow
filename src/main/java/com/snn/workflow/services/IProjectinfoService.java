package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Projectinfo;
import com.snn.workflow.entity.WxProjectItem;

import java.security.Provider;

/**
 * @className IProjectinfoService
 * @Author lulu
 * @Date 2019-11-19 23:43
 **/
public interface IProjectinfoService {

    ServiceResponse insertProject(Projectinfo projectinfo);

    ServiceResponse updateProject(Projectinfo projectinfo);

    ServiceResponse<Projectinfo> getProjectByProInsId(String ProcessInstanceId);

    ServiceResponse updateProjectState(String id, String state);
}
