package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Filetemplate;

/**
 * @className ITemplateService
 * @Author lulu
 * @Date 2019/12/3 下午3:51
 **/
public interface ITemplateService {

    ServiceResponse selectAll();

    ServiceResponse addOrder(Filetemplate filetemplate);

    ServiceResponse deleteOrder(Integer id);

    ServiceResponse updateOrder(Filetemplate filetemplate);
}

