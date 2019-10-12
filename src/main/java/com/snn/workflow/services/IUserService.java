package com.snn.workflow.services;

import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.User;


/**
 * @className IUserService
 * @Author lulu
 * @Date 2019-09-01 23:00
 **/
public interface IUserService {

    ServiceResponse<User> login(String username, String password);

    ServiceResponse<String> register(User user);

    ServiceResponse<String> checkVaild(String str, String type);

    ServiceResponse selectQuestion(String username);

    ServiceResponse<String> checkAnswer(String username, String question, String answer);

    ServiceResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServiceResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServiceResponse<User> updateInformation(User user);

    ServiceResponse<User> getInfomation(Integer userId);

    ServiceResponse checkAdminRole(User user);
}
