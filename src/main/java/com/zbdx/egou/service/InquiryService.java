package com.zbdx.egou.service;

import com.zbdx.egou.pojo.Inquiry;

import java.util.List;

public interface InquiryService {
    Integer insert(Inquiry inquiry);
    List<Inquiry> selectAll(Integer pageCurrent,List<Integer>  carIds);
}
