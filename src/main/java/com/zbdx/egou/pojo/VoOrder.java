package com.zbdx.egou.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VoOrder {
    private Order order;
    private CarInfo carInfo;
}
