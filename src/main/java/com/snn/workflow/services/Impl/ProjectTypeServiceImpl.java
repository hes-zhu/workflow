package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.TDictMapper;
import com.snn.workflow.entity.TDict;
import com.snn.workflow.services.IProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className ProjectTypeServiceImpl
 * @Author SNN
 * @Date 2019/10/28 20:58
 **/
@Service("iProjectTypeService")
public class ProjectTypeServiceImpl implements IProjectTypeService {

    @Autowired
    private TDictMapper tDictMapper;

    @Override
    public ServiceResponse getAllData() {
        List<TDict> list = tDictMapper.selectAll();
        return ServiceResponse.createBySuccess(list);
    }

    @Override
    public ServiceResponse insertProject(TDict tDict) {
        int result = tDictMapper.insert(tDict);
        if (result == 0) {
            return ServiceResponse.createByErrorMessage("新添失败");
        }
        return ServiceResponse.createBySuccess("新添成功");
    }

    @Override
    public ServiceResponse updateProject(TDict tDict) {
        int result = tDictMapper.updateByPrimaryKey(tDict);
        if (result == 0) {
            return ServiceResponse.createByErrorMessage("更新失败");
        }
        return ServiceResponse.createBySuccess("更新成功");
    }

    @Override
    public ServiceResponse deleteProject(Integer id) {
        int result = tDictMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServiceResponse.createByErrorMessage("删除失败");
        }
        return ServiceResponse.createBySuccess("删除成功");
    }
}
