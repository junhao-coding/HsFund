package com.fund.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 按照业务规则生成申购和赎回的订单id
 */
public class BusinessIdGenerator {
    public static Long generateBusinessId(){
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SS");
        String now = sdf.format(nowTime).replaceAll("-","");
        return Long.valueOf(now);
    }

}
