package com.zbdx.egou.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {
    private Integer  userId;
    private  String username;
    private  String password;
    private String city;
    private String telephone;
}
