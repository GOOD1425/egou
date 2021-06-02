package com.zbdx.egou.controller;

import com.zbdx.egou.pojo.CarInfo;
import com.zbdx.egou.pojo.Store;
import com.zbdx.egou.pojo.User;
import com.zbdx.egou.service.CarInfoService;
import com.zbdx.egou.service.StoreService;
import com.zbdx.egou.service.UserService;
import com.zbdx.egou.utils.IPutils;
import com.zbdx.egou.utils.MultipartFileToFile;
import com.zbdx.egou.utils.TencentUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/sell")
public class SellController {
    @Autowired
    UserService userService;
    @Autowired
    StoreService storeService;
    @Autowired
    CarInfoService carInfoService;
    @RequestMapping("/Info")
    @ResponseBody
    public String upInfo(MultipartFile multipartFile,String ctype,String brand,String color,String month,String mileage,String price,String type,String username,String model) throws Exception {
        CarInfo carInfo=new CarInfo();
        String  addr= IPutils.getAddr();
        File file = MultipartFileToFile.multipartFileToFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        Double dmileage=Double.parseDouble(mileage);
        Double dprice=Double.parseDouble(price);
        System.out.println(dmileage);
        System.out.println(dprice);
        String path = TencentUploadUtil.uploadFile("dintalk/carImages/" + fileName, file);
        MultipartFileToFile.delteTempFile(file);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String  tel=userService.selectByUsername(username).getTelephone();
        Integer  belong=userService.selectByUsername(username).getUserId();

       carInfo.setType(ctype).setBrand(brand).setArea(addr).setProdate(sdf.parse(month)).setMileage(dmileage).setPrice(dprice)
       .setTelephone(tel).setAppearpic(path).setCarcolor(color).setBelongType(type).setBelong(belong).setModel(model);
        String s = carInfoService.insertCarInfo(carInfo);
     //   String s = "error";
        if(s!=null){
            return "ok";
        }else{
            return "error";
        }
    }
    @RequestMapping("/selectMyCar")
    @ResponseBody
    public List<CarInfo> selectMyCar(Integer pageCurrent,String username,String type){
        List<CarInfo> carInfos=null;
        if (type.equals("user")){
            User user = userService.selectByUsername(username);
            carInfos= carInfoService.selectByNameType(pageCurrent,user.getUserId(), type);
        }else if(type.equals("admin")){
            Store store = storeService.selectByUsername(username);
           carInfos = carInfoService.selectByNameType(pageCurrent,store.getStoreId(), type);
        }
       return carInfos;
    }
}
