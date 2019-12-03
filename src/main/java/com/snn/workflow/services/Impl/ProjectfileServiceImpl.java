package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.ProjectfileMapper;
import com.snn.workflow.entity.Projectfile;
import com.snn.workflow.services.IProjectfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className ProjectfileServiceImpl
 * @Author lulu
 * @Date 2019/11/27 下午7:00
 **/
@Service("projectfileService")
public class ProjectfileServiceImpl implements IProjectfileService {

    @Autowired
    private ProjectfileMapper projectfileMapper;

    @Override
    public ServiceResponse showFile(Integer projectId) {
        List<Projectfile> data = projectfileMapper.selectByProjectId(projectId);
        if (data != null) {
            return ServiceResponse.createBySuccess(data);
        }
        return ServiceResponse.createByError();
    }
}
