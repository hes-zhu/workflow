package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.Opinion;

/**
 * @className OpinionService
 * @Author lulu
 * @Date 2019/11/8 下午10:58
 **/
public interface IOpinionService {
    ServiceResponse insert(Opinion opinion);

    ServiceResponse getOpinionById(Integer id);
}
