package com.zbdx.egou.service.impl;

import com.zbdx.egou.dao.InquiryDao;
import com.zbdx.egou.pojo.Inquiry;
import com.zbdx.egou.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {
    @Autowired
    InquiryDao inquiryDao;
    @Override
    public Integer insert(Inquiry inquiry) {
        return inquiryDao.insert(inquiry);
    }

    @Override
    public List<Inquiry> selectAll(Integer pageCurrent, List<Integer> carIds){
        if(pageCurrent!=null) pageCurrent=(pageCurrent-1)*3;
        return inquiryDao.selectAll(pageCurrent,carIds);
    }
}
