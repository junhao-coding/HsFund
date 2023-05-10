package com.fund.liquidation;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/08  13:56
 */
@EnableCloudDataSource
@CloudApplication
public class LiquidationStarter {
    public static void main(String[] args) {
        CloudBootstrap.run(LiquidationStarter.class, args);
    }
}
