package com.snn.workflowdemo.controller;

import com.snn.workflowdemo.common.ServiceResponse;
import com.snn.workflowdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @className AdminController
 * @Author lulu
 * @Date 2019-09-01 23:05
 **/
@RestController
@RequestMapping("/api/admin/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello")
    public ServiceResponse hello() {
        System.out.println("hello spboot");
        return ServiceResponse.createBySuccessMessage("hello spboot");
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    /**
     * @auther: lulu
     * @Description: 测试数据库连接成功与否
     * @MethodName: getUser
     * @return: com.snn.workflowdemo.common.ServiceResponse
     * @param: []
     * @date: 2019-09-10 13:41
     **/
    public ServiceResponse getUser() {
        System.out.println(userService.listAll());
        return ServiceResponse.createBySuccess("查询成功", userService.listAll());
    }

}
