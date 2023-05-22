package com.fund.api.dto;

import com.fund.api.entity.BankCard;
import com.fund.api.entity.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientDTO extends Client {
    private List<BankCard> bankCardList;
}
