package com.zbdx.egou.controller;

import com.zbdx.egou.pojo.CarInfo;
import com.zbdx.egou.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/buycar")
public class BuyController {
    @Autowired
    CarInfoService carInfoService;
    @RequestMapping("/getCar")
    @ResponseBody
    public List<CarInfo> show(String args,Integer pageCurrent){
        String[] split = args.split(";");
        List<String> list=new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
          String a=  split[i].split(":")[1];
          list.add(a);
        }
        String ctype=list.get(0).equals("不限")?null:list.get(0);
        String price=list.get(1);
        Double sprice,eprice;
        switch (price) {
            case "不限"  : {sprice=null;eprice=null;} break;
            case "3万内"  : {sprice=0.0;eprice=3.0; }break;
            case "3-8万"   : {sprice=3.0;eprice=8.0; }break;
            case "8-15万"  : {sprice=8.0;eprice=15.0; }break;
            case "15-30万"  : {sprice=15.0;eprice=30.0; }break;
            case "30-50万" : {sprice=30.0;eprice=50.0;} break;
            case "50-100万" : {sprice=50.0;eprice=100.0; }break;
            case "100万以上" : {sprice=100.0;eprice=null;} break;
            default:
                throw new IllegalStateException("Unexpected value: " + price);
        }
        String model=list.get(2).equals("不限")?null:list.get(2);
        String age=list.get(3);
        Date date=new Date();
        Date sage,eage;
        switch (age){
            case "车龄" :{sage=null;eage=null;}break;
            case "1年内" :{sage=new Date(new Date().getTime()- 1000L *60*60*24*365);eage=date;}break;
            case "2年内" :{sage=new Date(new Date().getTime()- 2L *1000*60*60*24*365);eage=date;}break;
            case "3年内" :{sage=new Date(new Date().getTime()- 3L *1000*60*60*24*365);eage=date;}break;
            case "3-5年" :{sage=new Date(new Date().getTime()- 5L *1000*60*60*24*365);eage=new Date(date.getTime()- 3L *1000*60*60*24*365);}break;
            case "5-8年" :{sage=new Date(new Date().getTime()- 8L *1000*60*60*24*365);eage=new Date(date.getTime()- 5L *1000*60*60*24*365);}break;
            case "8年以上" :{sage=null;eage=new Date(new Date().getTime()- 8L *1000*60*60*24*365);}break;
            default:
                throw new IllegalStateException("Unexpected value: " + age);
        }
        String mileage=list.get(4);
        Double resmileage;
        switch (mileage){
            case "里程" :{resmileage=null;} break;
            case "1万公里内" :{resmileage=1.0;} break;
            case "3万公里内" :{resmileage=3.0;} break;
            case "5万公里内" :{resmileage=5.0;} break;
            case "10万公里内" :{resmileage=10.0;} break;
            default:
                throw new IllegalStateException("Unexpected value: " + mileage);
        }
        String color=list.get(5);
        List<String> colors=new ArrayList<>();
        if(color.equals("颜色")){
            colors=null;
        }else{
            String[] split1 = color.split("\\+");
            if(split1.length!=0){
                for (int i = 0; i < split1.length; i++) {
                    colors.add(split1[i]);
                }
            }
        }
        return    carInfoService.selectByArg(ctype,sprice,eprice,model,sage,eage,resmileage,colors, pageCurrent );
    }
    @RequestMapping("/selectById")
    @ResponseBody
    public CarInfo selectById( Integer carId){
        return  carInfoService.selectById(carId) ;
    }
    @RequestMapping("/all")
    @ResponseBody
    public List<CarInfo> selectAll(){
        return  carInfoService.selectAll() ;
    }
    @RequestMapping("/byPage")
    @ResponseBody
    public List<CarInfo> byPage(Integer pageCurrent){
        return carInfoService.byPage(pageCurrent);
    }
    @RequestMapping("/detail")
    public String showDetail(){
        return "/detail";
    }
}
