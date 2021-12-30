package com.zbdx.egou.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@Accessors(chain = true)
public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer carId;
    private Integer deposit;
    private Double due ;
    private Integer isDelete;
    private Date date;
}
