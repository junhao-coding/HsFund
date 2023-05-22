package com.fund.api.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/19  16:10
 */
public class SystemDateUtil {
    private static LocalDate CURRENT_DATE = LocalDate.now();

    public static void dayInitial(){
        do{
            CURRENT_DATE = CURRENT_DATE.plusDays(1);
        }while(CURRENT_DATE.getDayOfWeek() == DayOfWeek.SATURDAY || CURRENT_DATE.getDayOfWeek() == DayOfWeek.SUNDAY);
    }
    public static LocalDate current(){
        return CURRENT_DATE;
    }
}
