package com.zbdx.egou.controller;

import com.zbdx.egou.pojo.CarInfo;
import com.zbdx.egou.pojo.Collection;
import com.zbdx.egou.pojo.VoCollect;
import com.zbdx.egou.service.CarInfoService;
import com.zbdx.egou.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    CarInfoService carInfoService;
    @RequestMapping("/collect")
    @ResponseBody
    public String collect(Collection collection){
        Integer insert = collectionService.insert(collection);
        if(insert>0){
            return "ok";

        }
        return "error";
    }
    @RequestMapping("/selectCollection")
    @ResponseBody
    public List<VoCollect> selectCollection(Integer pageCurrent, String username ){
        List<Collection> collections = collectionService.selectByUsername(username);
        List<VoCollect> infos = new ArrayList<>();
       if(collections.size()!=0){
           List<Integer> carIds=new ArrayList<>();
           for (int i = 0; i < collections.size(); i++) {
               carIds.add(collections.get(i).getCarId());
           }
           List<CarInfo> info= carInfoService.selectByIds(carIds, pageCurrent);
           for (int i = 0; i < info.size(); i++) {
               VoCollect collect=new VoCollect();
               CarInfo info1 = info.get(i);
               Integer id = collections.get(i).getId();
               collect.setCarInfo(info1);
               collect.setCollectId(id);
               infos.add(collect);
           }
       }
        return infos;
    }
    @RequestMapping("/deletecollect")
    @ResponseBody
    public String deleteCollect(Integer collectId){
        Integer collect = collectionService.deleteCollect(collectId);
        if(collect!=0){
            return "ok";
        }else{
            return "error";
        }
    }
}
