package com.zbdx.egou.controller;

import com.alibaba.fastjson.JSON;
import com.zbdx.egou.service.CarList;
import com.zbdx.egou.utils.IPutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @title: PageController
 * @Author Hao
 * @Date: 2021-2-10 15:59
 * @Version 1.0
 */
@Controller
public class PageController {
    @Autowired
    CarList carList;
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return  page;
    }

    @RequestMapping("/account/register")
    public String register( ){
        return  "register";
    }
    @RequestMapping("/getIp")
    @ResponseBody
    public String getAddr() throws IOException {
        String  addr= IPutils.getAddr();
        return addr;
    }
    @RequestMapping("/getCarType")
    @ResponseBody
    public List<List<String>> getCarType(){
        return  carList.selectByFirst();
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Map<String,String>> selectAll(){
        return carList.selectAll();
    }
    @RequestMapping("/selectCtype")
    @ResponseBody
    public List<Map<String,String>> selectCtype(String brandId){
        return carList.selectByBrandId(brandId);
    }

}
