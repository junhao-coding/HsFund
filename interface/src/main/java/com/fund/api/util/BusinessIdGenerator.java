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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String now = sdf.format(nowTime).replaceAll("-","");
        Random random = new Random();
        return Long.valueOf(now + (random.nextInt(90000) + 10000));
    }
}
