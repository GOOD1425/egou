package com.zbdx.egou.controller;

import com.zbdx.egou.pojo.*;
import com.zbdx.egou.service.CarInfoService;
import com.zbdx.egou.service.InquiryService;
import com.zbdx.egou.service.StoreService;
import com.zbdx.egou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class InquiryController {
    @Autowired
    InquiryService inquiryService;
    @Autowired
    UserService userService;
    @Autowired
    CarInfoService carInfoService;
    @Autowired
    StoreService storeService;
    @RequestMapping("/xunjia")
    @ResponseBody
    public String xunjia(String username,Integer carId){
        User user = userService.selectByUsername(username);
        Date date=new Date();
        Inquiry inquiry=new Inquiry();
        inquiry.setCarId(carId).setTelephone(user.getTelephone()).setDate(date).setIsdelete(0).setUserId(user.getUserId());
        Integer insert = inquiryService.insert(inquiry);
        if(insert!=0){
            return "ok";
        }
        return "error";
    }
    @RequestMapping("/selectInquiry")
    @ResponseBody
    public List<VoInquiry> selectInquiry(Integer pageCurrent,String username,String type){
        List<CarInfo> carInfos=null;
        if (type.equals("user")){
            User user = userService.selectByUsername(username);
            carInfos= carInfoService.selectByNameType(pageCurrent,user.getUserId(), type);
        }else if(type.equals("admin")){
            Store store = storeService.selectByUsername(username);
            carInfos = carInfoService.selectByNameType(pageCurrent,store.getStoreId(), type);
        }
        List<Integer> ids=new ArrayList<>();
        for (int i = 0; i < carInfos.size(); i++) {
            ids.add(carInfos.get(i).getCarId());
        }
        List<Inquiry> inquiries = inquiryService.selectAll(pageCurrent, ids);
        List<VoInquiry> list=new ArrayList<>();
        for (int i = 0; i <inquiries.size() ; i++) {
            VoInquiry voInquiry=new VoInquiry();
            CarInfo carInfo = carInfoService.selectById(inquiries.get(i).getCarId());
            voInquiry.setCarInfo(carInfo);
            voInquiry.setInquiry(inquiries.get(i));
            list.add(voInquiry);
        }
        return  list;
    }
    @RequestMapping("/shenhe")
    @ResponseBody
    public  String shenhe(Integer InquiryId){
        Integer shenhe = inquiryService.shenhe(InquiryId);
        if(shenhe!=0){
            return "ok";
        }
        return "error";
    }
}
