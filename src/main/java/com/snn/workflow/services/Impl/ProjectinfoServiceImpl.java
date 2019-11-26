package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ResponseCode;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.ProjectinfoMapper;
import com.snn.workflow.entity.Projectinfo;
import com.snn.workflow.services.IProjectinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className ProjectinfoServiceImpl
 * @Author lulu
 * @Date 2019-11-19 23:43
 **/
@Service("projectinfoService")
public class ProjectinfoServiceImpl implements IProjectinfoService {

    @Autowired
    private ProjectinfoMapper projectinfoMapper;

    @Override
    public ServiceResponse insertProject(Projectinfo projectinfo) {
        int resultCount = projectinfoMapper.insert(projectinfo);
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("项目创建失败");
        }
        return ServiceResponse.createBySuccessMessage("项目创建成功");
    }

    @Override
    public ServiceResponse updateProject(Projectinfo projectinfo) {
        int resultCount = projectinfoMapper.updateByProcessInstanceId(projectinfo);
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("项目更新失败");
        }
        return ServiceResponse.createBySuccessMessage("项目更新成功");
    }

    @Override
    public ServiceResponse<Projectinfo> getProjectByProInsId(String ProcessInstanceId) {
        if (ProcessInstanceId.isEmpty()) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Projectinfo projectinfo = projectinfoMapper.getProjectByProInsId(Integer.parseInt(ProcessInstanceId));
        if (projectinfo == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return ServiceResponse.createBySuccess(projectinfo);
    }

    @Override
    public ServiceResponse updateProjectState(String id, String state) {
        int resultCount = projectinfoMapper.updateProjectState(Integer.parseInt(id), Integer.parseInt(state));
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("项目状态更新失败");
        }
        return ServiceResponse.createBySuccessMessage("项目状态更新成功");
    }
}
