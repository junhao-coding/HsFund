package com.fund.liquidation.config;


import com.fund.api.util.SystemDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/21  9:32
 */
@Component
@Slf4j
public class CronTask {
    /**
     * 周一到周五刷新系统日期
     */
    @Scheduled(cron="0 0 9 ? * MON-FRI")
    public void autoDayInitial(){
        SystemDateUtil.dayInitial();
        log.info("目前系统日期是{}", SystemDateUtil.current());
    }
}
