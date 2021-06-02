package com.zbdx.egou.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Store implements Serializable {
    private Integer  storeId;
    private  String sname;
    private  String name;
    private  String username;
    private String password;
    private String tel;
}
