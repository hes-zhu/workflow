package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @className IModelerService
 * @Author SNN
 * @Date 2019/9/23 20:47
 **/
public interface IModelerService {

    ServiceResponse newModel();

    ServiceResponse deploy(String id) throws Exception;

    Object getAllModel(Integer rowSize, Integer page);

    ServiceResponse getOneModel(String id);

    ServiceResponse deleteOneModel(String id);

    ServiceResponse putOneModel(String id);
}
