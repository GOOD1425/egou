package com.zbdx.egou.utils;

import com.zbdx.egou.pojo.Store;
import com.zbdx.egou.service.StoreService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    StoreService storeService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Store store = null;
        // 1. 把AuthenticationToken转换为CustomizedToken
        CustomizedToken customizedToken = (CustomizedToken) token;
        // 2. 从CustomizedToken中获取username
        String username = customizedToken.getUsername();
        // 3. 若用户不存在，抛出UnknownAccountException异常
        store = storeService.selectByUsername(username);
        if (store == null)
            throw new UnknownAccountException("用户不存在！");
        // 4.
        // 根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为SimpleAuthenticationInfo
        // 以下信息从数据库中获取
        // （1）principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象
        Object principal = store;
        // （2）credentials：密码
        Object credentials = store.getPassword();
        // （3）realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials,
                realmName);
        return info;
    }
}
