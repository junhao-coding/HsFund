package com.fund.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/10  12:41
 */
@EnableCloudDataSource
@CloudApplication
public class ClientStarter {
    public static void main(String[] args) {
        CloudBootstrap.run(ClientStarter.class, args);
    }
}
