package com.fund.api.dto;

import com.fund.api.entity.BankCard;
import com.fund.api.entity.Client;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO extends Client {
    private List<BankCard> bankCardList;
}
