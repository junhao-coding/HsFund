package com.fund.api.service;

import com.fund.api.dto.BusinessDTO;
import com.fund.api.dto.Page;
import com.fund.api.entity.Business;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CloudService
public interface BusinessService {
    void addFundPurchase(Business business);

    List<Business> getBusinessConfirmed(LocalDate date);

    void confirmBusiness(LocalDate date);

    void addFundSell(Business business);

    Page<BusinessDTO> getRecords(Map<String,Object> map);

    void withdrawBusiness(String businessId);
}
