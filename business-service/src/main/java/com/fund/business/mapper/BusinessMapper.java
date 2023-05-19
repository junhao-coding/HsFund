package com.fund.business.mapper;

import com.fund.api.entity.Business;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    /**
     * 将申购申请写入数据库
     * @param business
     */
    void insertPurchaseFund(Business business);
}
