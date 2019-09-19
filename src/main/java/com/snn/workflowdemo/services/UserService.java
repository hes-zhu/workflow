package com.snn.workflowdemo.services;

import com.snn.workflowdemo.entity.User;

import java.util.List;

/**
 * @className AdminService
 * @Author lulu
 * @Date 2019-09-01 23:00
 **/
public interface UserService {

    List<User> listAll();
}
