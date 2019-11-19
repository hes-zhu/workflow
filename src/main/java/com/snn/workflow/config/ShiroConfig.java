package com.snn.workflow.config;

import com.snn.workflow.common.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className ShiroConfig
 * @Author lulu
 * @Date 2019-11-15 19:20
 **/
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 添加shiro内置过滤器
         * 常用的过滤器：
         *      anon: 无需登录即可访问
         *      authc: 必须认证才可以访问
         *      user: 如果使用rememberMe的功能可以直接访问
         *      perms: 该资源必须得到资源权限才可以访问
         *      role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        filterMap.put("login", "anon");
        filterMap.put("index", "authc");
        //  filterMap.put("", "");

        // 授权拦截
        filterMap.put("add", "perms[user:add]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth ");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     * @return UserRealm
     */
    @Bean("userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }
}
