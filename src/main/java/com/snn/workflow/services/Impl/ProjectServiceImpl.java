package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ResponseCode;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.WxProjectItemMapper;
import com.snn.workflow.entity.WxProjectItem;
import com.snn.workflow.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className ProjectServiceImpl
 * @Author SNN
 * @Date 2019/10/15 22:12
 **/
@Service("iProjectService")
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private WxProjectItemMapper wxProjectItemMapper;

    @Override
    public ServiceResponse<WxProjectItem> insertProject(WxProjectItem wxProjectItem) {
        int resultCount = wxProjectItemMapper.insert(wxProjectItem);
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("项目创建失败");
        }
        return ServiceResponse.createBySuccessMessage("项目创建成功");
    }

    @Override
    public ServiceResponse updateProject(WxProjectItem wxProjectItem, String ProcessInstanceId) {
        int resultCount = wxProjectItemMapper.updateByProcessInstanceId(wxProjectItem);
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("项目更新失败");
        }
        return ServiceResponse.createBySuccessMessage("项目更新成功");
    }

    @Override
    public ServiceResponse getProjectByProInsId(String ProcessInstanceId) {
        if (ProcessInstanceId.isEmpty()) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return ServiceResponse.createBySuccess(wxProjectItemMapper.getProjectByProInsId(Integer.parseInt(ProcessInstanceId)));
    }
}














