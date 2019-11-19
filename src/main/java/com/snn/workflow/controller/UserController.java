package com.snn.workflow.controller;

import com.snn.workflow.common.Const;
import com.snn.workflow.common.ResponseCode;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.entity.User;
import com.snn.workflow.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @className UserController
 * @Author lulu
 * @Date 2019-09-01 23:05
 **/
@Api("用户信息管理")
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation("常规登录")
    @PostMapping(value = "login")
    /**
     * @auther: SNN
     * @Description: 常规登录
     * @MethodName: login
     * @return: com.mmall.common.ServiceResponse<com.snn.workflow.entity.User>
     * @param: [username, password, session]
     * @date: 2019/6/17 0:46
     **/
    public ServiceResponse<User> login(@ApiParam("用户名") @RequestParam("username") String username, @ApiParam("密码") @RequestParam("password") String password, HttpSession session) {
        ServiceResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
//        session.setAttribute(Const.CURRENT_USER, response.getData());
    }

        return response;
    }

    @ApiOperation("注销登录")
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    /**
     * @auther: SNN
     * @Description: 注销登录
     * @MethodName: logout
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [session]
     * @date: 2019/6/17 0:46
     **/
    public ServiceResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServiceResponse.createBySuccess();
    }

    @ApiOperation("注册用户")
    @RequestMapping(value = "register", method = RequestMethod.POST) 
    /**
     * @auther: SNN
     * @Description: 注册用户
     * @MethodName: register
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [user]
     * @date: 2019/6/17 0:46
     **/
    public ServiceResponse<String> register(@ApiParam("用户信息") User user) {
        return iUserService.register(user);
    }

    @ApiOperation("检查用户名/邮箱是否有效")
    @RequestMapping(value = "check_valid", method = RequestMethod.POST)
    /**
     * @auther: SNN
     * @Description: 检查用户名是否有效
     * @MethodName: checkValid
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [str, type]
     * @date: 2019/6/17 0:46
     **/
    public ServiceResponse<String> checkValid(@ApiParam("用户名/邮箱") @RequestParam("str") String str, @ApiParam("类型：email/username") @RequestParam("type") String type) {
        return iUserService.checkVaild(str, type);
    }

    @ApiOperation("得到当前登录用户信息")
    @RequestMapping(value = "get_user_info", method = RequestMethod.POST) 
    /**
     * @auther: SNN
     * @Description: 得到当前登录用户信息
     * @MethodName: getUserInfo
     * @return: com.mmall.common.ServiceResponse<com.snn.workflow.entity.User>
     * @param: [session]
     * @date: 2019/6/17 0:47
     **/
    public ServiceResponse<User> getUserInfo(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user != null) {
            return ServiceResponse.createBySuccess(user);
        }
        return ServiceResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    @ApiOperation("忘记密码时获取问题")
    @RequestMapping(value = "forget_get_question", method = RequestMethod.POST) 
    /**
     * @auther: SNN
     * @Description: 忘记密码的问题
     * @MethodName: forgetGetQuestion
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [username]
     * @date: 2019/6/17 0:47
     **/
    public ServiceResponse<String> forgetGetQuestion(@ApiParam("用户名") @RequestParam("username") String username) {
        return iUserService.selectQuestion(username);
    }

    @ApiOperation("忘记密码的答案校验")
    @RequestMapping(value = "forget_check_answer", method = RequestMethod.POST) 
    /**
     * @auther: SNN
     * @Description: 忘记密码的答案校验
     * @MethodName: forgetCheckAnswer
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [username, question, answer]
     * @date: 2019/6/17 0:48
     **/
    public ServiceResponse<String> forgetCheckAnswer(@ApiParam("用户名") @RequestParam("username") String username, @ApiParam("问题") @RequestParam("question") String question, @ApiParam("答案") @RequestParam("answer") String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }

    @ApiOperation("忘记密码后的重置密码")
    @RequestMapping(value = "forget_reset_password", method = RequestMethod.POST) 
    /**
     * @auther: SNN
     * @Description: 忘记密码后的重置密码
     * @MethodName: forgetResetPassword
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [username, passwordNew, forgetToken]
     * @date: 2019/6/17 0:48
     **/
    public ServiceResponse<String> forgetResetPassword(@ApiParam("用户名") @RequestParam("username") String username, @ApiParam("新密码") @RequestParam("passwordNew") String passwordNew, @ApiParam("Token令牌") @RequestParam("forgetToken") String forgetToken) {
        return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    @ApiOperation("登录中状态重置密码")
    @RequestMapping(value = "reset_password", method = RequestMethod.POST) 
    /**
     * @auther: SNN
     * @Description: 登录中状态重置密码
     * @MethodName: resetPassword
     * @return: com.mmall.common.ServiceResponse<java.lang.String>
     * @param: [session, passwordOld, passwordNew]
     * @date: 2019/6/17 0:49
     **/
    public ServiceResponse<String> resetPassword(HttpSession session, @ApiParam("旧密码") @RequestParam("passwordOld") String passwordOld, @ApiParam("新密码") @RequestParam("passwordNew") String passwordNew) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServiceResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }

    @ApiOperation("登录状态更新个人信息")
    @RequestMapping(value = "update_information", method = RequestMethod.POST)
    /**
     * @auther: SNN
     * @Description: 登录状态更新个人信息
     * @MethodName: update_information
     * @return: com.mmall.common.ServiceResponse<com.snn.workflow.entity.User>
     * @param: [session, user]
     * @date: 2019/6/17 0:49
     **/
    public ServiceResponse<User> update_information(HttpSession session, @ApiParam("用户信息") User user) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null) {
            ServiceResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        ServiceResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @ApiOperation("获取当前登录用户的详细信息，并强制登录")
    @RequestMapping(value = "get_information", method = RequestMethod.POST)
    /**
     * @auther: SNN
     * @Description: 获取当前登录用户的详细信息，并强制登录
     * @MethodName: get_information
     * @return: com.mmall.common.ServiceResponse<com.snn.workflow.entity.User>
     * @param: [session]
     * @date: 2019/6/17 0:50
     **/
    public ServiceResponse<User> get_information(HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，请先登录");
        }
        return iUserService.getInfomation(currentUser.getId());
    }

    @PostMapping
    public String loginto(@ApiParam("用户名") @RequestParam("username") String username, @ApiParam("密码") @RequestParam("password") String password, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            return "success";
        } catch (UnknownAccountException e) {
            return "账号不存在";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
    }

}
