package com.fund.api.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 根据提交时间生成预计确认时间
 */
public class ExceptedDayGenerator {
    public static Date generateExceptedDay(){
        LocalDateTime now = LocalDateTime.now();    // 交易申请时间【年月日时分秒】
        LocalTime currentTime = LocalTime.now();    // 交易申请时间【时分秒】
        LocalTime afternoonThree = LocalTime.of(15, 0); //下午3点钟
        OffsetDateTime offsetDT = OffsetDateTime.now(ZoneOffset.of("+8"));
        int todayOfWeek = offsetDT.getDayOfWeek().getValue(); //今天星期三
        LocalDateTime exceptedTime = null;
        switch(todayOfWeek) {
            case 1:
            case 2:
            case 3:
                if(currentTime.isBefore(afternoonThree)){
                    exceptedTime = now.plusDays(1);
                }else{
                    exceptedTime = now.plusDays(2);
                }
                break;
            case 4:
                if(currentTime.isBefore(afternoonThree)){
                    exceptedTime = now.plusDays(1);
                }else{
                    exceptedTime = now.plusDays(4);
                }
                break;
            case 5:
                if(currentTime.isBefore(afternoonThree)){
                    exceptedTime = now.plusDays(3);
                }else{
                    exceptedTime = now.plusDays(4);
                }
                break;
            case 6:
                exceptedTime = now.plusDays(3);
                break;
            case 7:
                exceptedTime = now.plusDays(2);
                break;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String nowTime = now.format(formatter);
        //System.out.println("交易申请时间：" + nowTime + "  星期" + todayOfWeek);
        Date exceptedDay = Date.from(exceptedTime.atZone(ZoneId.systemDefault()).toInstant());
        return exceptedDay;
    }
}
