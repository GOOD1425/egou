package com.zbdx.egou.dao;

import com.zbdx.egou.pojo.Inquiry;

import java.util.List;

public interface InquiryDao {
    Integer insert(Inquiry inquiry);
    List<Inquiry> selectAll(Integer pageCurrent,List<Integer>  carIds);
    Integer shenhe(Integer InquiryId);
}
