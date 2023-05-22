package com.fund.liquidation.config;


import com.fund.api.service.LiquidationService;
import com.fund.api.util.SystemDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private LiquidationService liquidationService;
    /**
     * 周一到周五上午九点刷新系统日期
     */
    @Scheduled(cron="0 0 9 ? * MON-FRI")
    public void autoDayInitial(){
        log.info("正在进行日期初始化，当前系统日期是{}", SystemDateUtil.current());
        SystemDateUtil.dayInitial();
        log.info("日期初始化完成，目前系统日期是{}", SystemDateUtil.current());
    }

    /**
     * 周一到周五下午三点进行行情更新
     */
    @Scheduled(cron="0 0 15 ? * MON-FRI")
    public void autoMarketUpdate(){
        log.info("正在进行行情更新，当前系统日期是{}", SystemDateUtil.current());
        liquidationService.marketUpdate();
        log.info("行行情更新完成，当前系统日期是{}", SystemDateUtil.current());
    }
}
