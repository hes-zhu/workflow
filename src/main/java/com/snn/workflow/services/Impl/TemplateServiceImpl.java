package com.snn.workflow.services.Impl;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.dao.FiletemplateMapper;
import com.snn.workflow.entity.Filetemplate;
import com.snn.workflow.services.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className TemplateServiceImpl
 * @Author lulu
 * @Date 2019/12/3 下午3:51
 **/
@Service("templateService")
public class TemplateServiceImpl implements ITemplateService {
    @Autowired
    private FiletemplateMapper filetemplateMapper;


    @Override
    public ServiceResponse selectAll() {
        List list = filetemplateMapper.selectAll();
        return ServiceResponse.createBySuccess(list);
    }

    @Override
    public ServiceResponse addOrder(Filetemplate filetemplate) {
        Integer order = filetemplateMapper.insert(filetemplate);
        if (order == 0) {
            return ServiceResponse.createBySuccessMessage("添加记录失败");
        }
        return ServiceResponse.createBySuccess();
    }

    @Override
    public ServiceResponse deleteOrder(Integer id) {
        Integer order = filetemplateMapper.deleteByPrimaryKey(id);
        if (order == 0) {
            return ServiceResponse.createBySuccessMessage("删除记录失败");
        }
        return ServiceResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServiceResponse updateOrder(Filetemplate filetemplate) {
        Integer order = filetemplateMapper.updateByPrimaryKey(filetemplate);
        if (order == 0) {
            return ServiceResponse.createBySuccessMessage("更新记录失败");
        }
        return ServiceResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServiceResponse getTemplate(String projectType, Integer enable, String str) {
        String url = filetemplateMapper.getTemplate(projectType, enable, str);
        return ServiceResponse.createBySuccess(url);
    }
}
