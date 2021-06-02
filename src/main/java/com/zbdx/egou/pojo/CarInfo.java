package com.zbdx.egou.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@Accessors(chain=true)
public class CarInfo {
    private Integer  carId;
    private String type;
    private String brand;
    private String area;
    private Date prodate;
    private Double mileage;
    private Double price;
    private String telephone;
    private String appearpic;
    private String carcolor;
    private Integer belong;
    private Integer pageview;
    private String belongType;
    private Integer isdelete;
    private String model;
    private Date update;

}
