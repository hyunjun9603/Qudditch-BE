package com.goldensnitch.qudditch.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CustomerOrder {
    private Integer id;
    private Integer userCustomerId;
    private Integer userStoreId; // 가맹점 코드
    private Integer totalAmount;
    private Integer usedPoint;
    private Integer totalPay;
    private Integer earnPoint;
    private Date orderedAt;
    private String tid; // 결제 고유번호
}
