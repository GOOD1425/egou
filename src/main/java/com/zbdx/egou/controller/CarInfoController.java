package com.zbdx.egou.controller;

import com.zbdx.egou.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarInfoController {
    @Autowired
    CarInfoService carInfoService;
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer carId){
            Integer delete = carInfoService.delete(carId);
        if(delete!=0) {
            return "ok";
        }
        return "error";
    }
}
