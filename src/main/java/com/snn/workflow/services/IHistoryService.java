package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;

public interface IHistoryService {
    ServiceResponse historyProcessInstance();

    ServiceResponse queryHistoryAct();

    ServiceResponse queryHistoryTask();

    ServiceResponse queryHistoryProcessVariables();
}
