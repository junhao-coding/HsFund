package com.fund.api.service;

import com.fund.api.dto.BusinessDto;
import com.fund.api.entity.Business;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.time.LocalDate;
import java.util.List;

@CloudService
public interface BusinessService {
    void addFundPurchase(Business business);

    List<BusinessDto> getBusinessConfirmed(LocalDate date);

    void confirmBusiness(LocalDate date);
}
