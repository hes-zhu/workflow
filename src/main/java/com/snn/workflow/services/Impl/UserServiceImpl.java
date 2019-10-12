package com.snn.workflow.services.Impl;

import com.snn.workflow.common.Const;
import com.snn.workflow.common.ServiceResponse;
import com.snn.workflow.common.TokenCache;
import com.snn.workflow.dao.UserMapper;
import com.snn.workflow.entity.User;
import com.snn.workflow.services.IUserService;
import com.snn.workflow.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @className UserServiceImpl
 * @Author lulu
 * @Date 2019-09-01 23:02
 **/
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("用户名不存在");
        }
        // 密码登录MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username, md5Password);
        if(user == null) {
            return ServiceResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);

        /*String token = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKEN_PREFIX+username, token);*/

        return ServiceResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServiceResponse<String> register(User user) {

        ServiceResponse validResponse = this.checkVaild(user.getUsername(), Const.USERNAME);
        if(!validResponse.isSuccess()) {
            return validResponse;
        }

        validResponse = this.checkVaild(user.getEmail(), Const.EMAIL);
        if(!validResponse.isSuccess()) {
            return validResponse;
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        // MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServiceResponse.createByErrorMessage("注册失败");
        }
        return ServiceResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServiceResponse<String> checkVaild(String str, String type) {
//        if(StringUtils.isNotBlank(type) && (Const.USERNAME.equals(type) || Const.EMAIL.equals(type))) {
            // 开始校验
            if(Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0) {
                    return ServiceResponse.createByErrorMessage("用户名已经存在");
                }
            }
            if(Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServiceResponse.createByErrorMessage("Emil已经存在");
                }
            }
//        } else {
//            return ServiceResponse.createByErrorMessage("参数错误");
//        }
        return ServiceResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServiceResponse selectQuestion(String username) {
        ServiceResponse validResponse = this.checkVaild(username, Const.USERNAME);
        if(validResponse.isSuccess()) {
            // 用户不存在
            return ServiceResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestinByUsername(username);
        if(StringUtils.isNotBlank(question)) {
            return ServiceResponse.createBySuccess(question);
        }
        return ServiceResponse.createByErrorMessage("找回密码的问题是空的");
    }

    @Override
    public ServiceResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if(resultCount > 0) {
            // 说明问题及问题答案是这个用户的，并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username, forgetToken);
            return ServiceResponse.createBySuccess(forgetToken);
        }
        return ServiceResponse.createByErrorMessage("问题的答案错误");
    }

    @Override
    public ServiceResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if(!StringUtils.isNotBlank(forgetToken)) {
            return ServiceResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServiceResponse validResponse = this.checkVaild(username, Const.USERNAME);
        if(validResponse.isSuccess()) {
            // 用户不存在
            return ServiceResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(!StringUtils.isNotBlank(token)) {
            return ServiceResponse.createByErrorMessage("token无效或者过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            if(rowCount > 0) {
                return ServiceResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServiceResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        return ServiceResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServiceResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        // 防止横向越权，要校验一下这个用户的旧密码，一定要指定是这个用户，
        // 因为我们会查询一个count(1)，如果不指定id，那么结果就是true啦count>0
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if(resultCount > 0) {
            return ServiceResponse.createByErrorMessage("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0) {
            return ServiceResponse.createBySuccessMessage("密码更新成功");
        }
        return ServiceResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServiceResponse<User> updateInformation(User user) {
        //username是不能被更新的
        //Email也要进行一个校验，校验新的Email是不是已经存在，并且存在的Email如果相同的话，不能是我们当前的这个用户的
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if(resultCount > 0) {
            return ServiceResponse.createByErrorMessage("email已经存在，请更换Email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0) {
            return ServiceResponse.createBySuccess("更新个人信息成功", updateUser);
        }
        return ServiceResponse.createByErrorMessage("更新个人信息失败");
    }

    @Override
    public ServiceResponse<User> getInfomation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null) {
            return ServiceResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySuccess(user);
    }

    //校验是否是管理员
    @Override
    public ServiceResponse checkAdminRole(User user) {
        if(user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }
}
