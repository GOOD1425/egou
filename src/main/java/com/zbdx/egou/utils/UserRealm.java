package com.zbdx.egou.utils;

import com.zbdx.egou.dao.UserDao;
import com.zbdx.egou.pojo.User;
import com.zbdx.egou.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomizedToken customizedToken = (CustomizedToken) token;
        // 2. 从CustomizedToken中获取email
        String username = customizedToken.getUsername();
        // 3. 若用户不存在，抛出UnknownAccountException异常
       User user = userService.selectByUsername(username);

        if (user == null)
            throw new UnknownAccountException("用户不存在！");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
                getName());
        return info;
    }
}
