package com.zbdx.egou.controller;

import com.zbdx.egou.pojo.Store;
import com.zbdx.egou.service.StoreService;
import com.zbdx.egou.utils.CustomizedToken;
import com.zbdx.egou.utils.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/store")
public class StoreController {
    private static final String ADMIN_LOGIN_TYPE  = LoginType.ADMIN.toString();
    @Autowired
    StoreService storeService;
    @RequestMapping("/checkName")
    @ResponseBody
    public String checkName(String sname){
        Integer store = storeService.selectBysname(sname);
        if(store!=null){
            return "error";
        }
        return "ok";
    }
    @RequestMapping("/checkTel")
    @ResponseBody
    public String checkTel(String tel){

        Integer store = storeService.selectByTel(tel);
        if(store!=null){
            return "error";
        }
        return "ok";
    }
    @RequestMapping("/reg")
    @ResponseBody
    public String reg(Store store){
        store.setPassword(new Md5Hash(store.getPassword()).toString());
        storeService.insert(store);
        return "ok";
    }
    @RequestMapping("/checkLogin")
    @ResponseBody
    public String  checkLogin(String username, String password) {
        //获取subject对象
        Subject subject= SecurityUtils.getSubject();
            CustomizedToken customizedToken = new CustomizedToken(username, password, ADMIN_LOGIN_TYPE );
            customizedToken.setRememberMe(false);
            try {
                subject.login(customizedToken);
            }catch (AuthenticationException e){
                return "error";
             }
        return "ok";
    }
}
