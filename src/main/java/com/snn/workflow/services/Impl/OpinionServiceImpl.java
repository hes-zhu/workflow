package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ResponseCode;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.OpinionMapper;
import com.snn.workflow.entity.Opinion;
import com.snn.workflow.services.IOpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className OpinionServiceImpl
 * @Author lulu
 * @Date 2019/11/8 下午11:00
 **/
@Service("opinionService")
public class OpinionServiceImpl implements IOpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    @Override
    public ServiceResponse insert(Opinion opinion) {
        int result = opinionMapper.insert(opinion);
        if (result == 0) {
            return ServiceResponse.createByErrorMessage("插入失败");
        }
        return ServiceResponse.createBySuccessMessage("插入成功");
    }

    @Override
    public ServiceResponse getOpinionById(Integer projectId) {
        List result = opinionMapper.selectByProjectId(projectId);
        if (result == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        return ServiceResponse.createBySuccess("获取成功", result);
    }
}
