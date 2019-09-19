package com.snn.workflowdemo.services.Impl;

import com.snn.workflowdemo.dao.UserMapper;
import com.snn.workflowdemo.entity.User;
import com.snn.workflowdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className UserServiceImpl
 * @Author lulu
 * @Date 2019-09-01 23:02
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        List user = userMapper.selectAll();
        return user;
    }
}
