package com.zbdx.egou.config;


import com.zbdx.egou.utils.AdminRealm;
import com.zbdx.egou.utils.CustomizedModularRealmAuthenticator;
import com.zbdx.egou.utils.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by yster@foxmail.com 2018/5/17/017 20:18
 */
@Configuration
public class ShiroConfig {
    /**
     * 记住我：自动登录-1
     */
/*    @Bean
    public SimpleCookie getSimpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("rememberMe");
        simpleCookie.setMaxAge(20000000);
        return simpleCookie;
    }*/

    /**
     * 记住我：自动登录-2
     */
/*
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(getSimpleCookie());
        return cookieRememberMeManager;
    }
*/

    /**
     * 开启MD5加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher getMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }

    /**
     * 自定义Realm密码验证与加密
     * @return
     */
    @Bean(name = "userRealm")
    public Realm getUserRealm(){
        UserRealm customRealm = new UserRealm();
        customRealm.setCredentialsMatcher(getMatcher());
        return customRealm;
    }
    @Bean(name="adminRealm")
    public Realm getAdminRealm(){
        AdminRealm customRealm = new AdminRealm();
        customRealm.setCredentialsMatcher(getMatcher());
        return customRealm;
    }

    /**
     * 创建SecurityManager环境
     * @return
     */
    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager  securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setAuthenticator(modularRealmAuthenticator());

        List<Realm> list=new ArrayList<>();
        list.add(getAdminRealm());
        list.add(getUserRealm());
        securityManager.setRealms(list);

        return securityManager;
    }

    /**
     * Shiro在Web项目中的过滤
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getfilterFactoryBean(){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());
        /**
         *  只有在下面配置路径访问权限，Shiro才会执行自动跳转。
         *  如果使用Shiro注解权限，就只会报异常，
         *  就只能采用统一异常处理的方法。
         */
        //拦截器.
        Map<String,String> map = new LinkedHashMap<String,String>();
        filterFactoryBean.setLoginUrl("/index");
        filterFactoryBean.setSuccessUrl("/index");
        filterFactoryBean.setUnauthorizedUrl("/403");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/logout ","logout ");
        //除了匿名访问的静态资源外,所有请求都要被认证
        map.put("/**","anon");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }


    /**
     * 保证Shiro的声明周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro授权生效
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        CustomizedModularRealmAuthenticator modularRealmAuthenticator = new CustomizedModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

}