package com.goldensnitch.qudditch.dto.storeInput;


import lombok.Data;

import java.sql.Date;

@Data
public class StockInputReq {

    private Integer productId;
    private int positionId;
    private int qty;
    private Date expiredAt;
}
