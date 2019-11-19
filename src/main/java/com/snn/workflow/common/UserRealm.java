package com.snn.workflow.common;

import com.snn.workflow.entity.User;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @className UserRealm
 * @Author lulu
 * @Date 2019-11-15 20:44
 **/
public class UserRealm extends AuthorizingRealm {


    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        // 给资源惊进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        return info ;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        String username = "snn";
        String password = "123";

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if(!token.getUsername().equals(username)) {
             return null;
             // 自动抛出UnknownAccountException
        }

        return new SimpleAuthenticationInfo("", password, "");
    }
}
