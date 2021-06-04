package com.zbdx.egou.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@ToString
@Accessors(chain=true)
public class Inquiry {
    private  Integer id;
    private  Integer carId;
    private  String  telephone;
    private Date  date;
    private  Integer isdelete;
    private  Integer userId;
}
