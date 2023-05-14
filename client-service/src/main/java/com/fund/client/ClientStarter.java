package com.fund.client;

import com.hundsun.jrescloud.common.boot.CloudApplication;
import com.hundsun.jrescloud.common.boot.CloudBootstrap;
import com.hundsun.jrescloud.db.core.configuration.EnableCloudDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author JunHao Yu
 * @version 1.0
 * @Description: <br/>
 * @date 2023/05/10  12:41
 */
@EnableCloudDataSource
@CloudApplication
@EnableTransactionManagement
public class ClientStarter {
    public static void main(String[] args) {
        CloudBootstrap.run(ClientStarter.class, args);
    }
}
